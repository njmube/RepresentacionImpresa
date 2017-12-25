package com.cubetech.representacion.impresa.application.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cubetech.representacion.impresa.application.CuentaEmisorService;
import com.cubetech.representacion.impresa.application.error.InternalException;
import com.cubetech.representacion.impresa.application.error.NotFoundException;
import com.cubetech.representacion.impresa.domain.entidades.Configuracion;
import com.cubetech.representacion.impresa.domain.entidades.CuentaEmisor;
import com.cubetech.representacion.impresa.domain.entidades.Reporte;
import com.cubetech.representacion.impresa.domain.repositorio.ConfiguracionRepository;
import com.cubetech.representacion.impresa.domain.repositorio.CuentaEmisorRepository;
import com.cubetech.representacion.impresa.domain.repositorio.ReporteRepository;
import com.cubetech.representacion.impresa.domain.valueobjects.EmisorReporte;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CuentaEmisorServiceImpl implements CuentaEmisorService {
	
	private static String CONFIG_NAME = "reporteComprobanteTipo";

	private CuentaEmisorRepository cuentaEmisorRepository;
	private ConfiguracionRepository configuracionRepository;
	private ReporteRepository reporteRepository;
	
	@Autowired
	public CuentaEmisorServiceImpl(CuentaEmisorRepository cuentaEmisorRepository, ConfiguracionRepository configuracionRepository, 
																ReporteRepository reporteRepository){
		this.cuentaEmisorRepository 	= cuentaEmisorRepository;
		this.configuracionRepository 	= configuracionRepository;
		this.reporteRepository				= reporteRepository;
	}
	
	@Override
	public Reporte ObtenReporte(String cuenta, String emisor, String tipoComprobante) {
		Reporte ret = null;
		CuentaEmisor cta;
		EmisorReporte er = new EmisorReporte(cuenta, emisor, tipoComprobante);
		String predeterminado;
		Configuracion config;
		try{
			cta = this.cuentaEmisorRepository.findOne(er);
			if(cta == null)
				throw new NotFoundException("No se encontro Informacion de la cuenta");
			ret = this.reporte(cta.getReporte().getId());
		}catch(EmptyResultDataAccessException|NotFoundException  e){
			log.error("No hay configuracion emisorReporte {}", er, e);
		}
		
		if(ret == null){
			predeterminado = CONFIG_NAME + "_" + tipoComprobante;
			try{
				config = this.configuracionRepository.findOne(predeterminado);
				if(config == null) throw new NotFoundException("NO se encontro la configuración del reporte");
				ret = this.reporte(Long.valueOf(config.getValor()).longValue());
			}catch(EmptyResultDataAccessException|NotFoundException|NumberFormatException  e){
				log.error("Error al consultar el reporte por default: {}", predeterminado, e);
				throw new InternalException("No fue posible determinar el reporte a Utilizar");
			}
		}
		
		return ret;
	}
	
	private Reporte reporte(long id) throws NotFoundException{
		Reporte reporte;
		ClassPathResource resourse;
		
		try{
			reporte = this.reporteRepository.findById(id);
		}catch(EmptyResultDataAccessException e){
			log.error("ID no encontrado: {}", id, e);
			throw new NotFoundException("Id no encontrado: " + String.valueOf(id));
		}
		if(!reporte.isActivo()){
			log.error("El reporte no esta activo {}", reporte);
			throw new NotFoundException("El reporte no esta activo" + reporte.toString());
		}
		resourse = new ClassPathResource(reporte.getUbicacion().getUri());
		try{
			reporte.setReporte(resourse.getInputStream());
		}catch(IOException e){
			log.error("No se puedo Leer el recurso {}", reporte);
			throw new NotFoundException("No se puedo encontrar el reporte " + reporte.toString());
		}
		return reporte;
	}
}
