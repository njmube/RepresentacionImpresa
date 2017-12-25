package com.cubetech.representacion.impresa.application.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.cubetech.representacion.impresa.application.ComprobanteService;
import com.cubetech.representacion.impresa.application.ImpresionService;
import com.cubetech.representacion.impresa.application.assembler.ImpresionAssembler;
import com.cubetech.representacion.impresa.application.error.InternalException;
import com.cubetech.representacion.impresa.domain.entidades.ComprobanteEntidad;
import com.cubetech.representacion.impresa.domain.entidades.Configuracion;
import com.cubetech.representacion.impresa.domain.repositorio.ComprobanteEntidadRepository;
import com.cubetech.representacion.impresa.domain.repositorio.ConfiguracionRepository;
import com.cubetech.representacion.impresa.interfaces.dto.ArchivoRepDTO;
import com.cubetech.representacion.impresa.interfaces.dto.ImpresionDTO;
import com.cubetech.representacion.impresa.interfaces.restclient.ArchivoRepository;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Slf4j
@Service
public class ImpresionServiceImpl implements ImpresionService {
	
	private static String LOGO = "LOGO_CUBE";
	private static String CUENTA = "CUBE";
	
	private ComprobanteService comprobanteService;
	private ConfiguracionRepository configuracionRepository;
	private ArchivoRepository archivoRepository;
	private ImpresionAssembler impresionAssembler;
	private ComprobanteEntidadRepository comprobanteEntidadRepository;
	
	@Autowired
	public ImpresionServiceImpl(ComprobanteService comprobanteService, ConfiguracionRepository configuracionRepository, 
			ArchivoRepository archivoRepository, ComprobanteEntidadRepository comprobanteEntidadRepository){
		this.comprobanteService = comprobanteService;
		this.configuracionRepository = configuracionRepository;
		this.archivoRepository = archivoRepository;
		this.comprobanteEntidadRepository = comprobanteEntidadRepository;
		this.impresionAssembler = new ImpresionAssembler();
	}
	
	@Override
	public String generaRepresentacionImpresa(String cuenta, ImpresionDTO impresion) {
		ComprobanteEntidad comprobante;
		Configuracion logoConfig;
		ArchivoRepDTO logo;
		Map<String, Object> parametros;
		JasperPrint print;
		byte[] reporte;
		String fileBase64;
		String comprobanteString;
	
		try{
			reporte = Base64.getDecoder().decode(impresion.getComprobante());
			comprobanteString = new String(reporte);
			reporte = null;
			comprobante = this.comprobanteService.generaComprobanteEntidad(comprobanteString, cuenta, impresion.getEmisor() , impresion.getReceptor());
			logoConfig = this.configuracionRepository.findOne(LOGO);
			logo = this.archivoRepository.findbyCuentaCorrelacion(CUENTA, logoConfig.getValor());
			parametros = this.impresionAssembler.toParametrosReporte(comprobante, logo);
			
			try {
				print = JasperFillManager.fillReport(comprobante.getReporte().getReporte(), parametros, new JREmptyDataSource());
				reporte = JasperExportManager.exportReportToPdf(print);
				fileBase64 = Base64.getEncoder().encodeToString(reporte);
				publicReporte(fileBase64, comprobante.getUuid());
				this.comprobanteEntidadRepository.save(comprobante);
			} catch (JRException e) {
				log.error("Error al generar el reporte Impreso {}", comprobante, e);
				throw new InternalException("Error al generar el reporte Impreso");
			}
		}catch(Exception e){
			log.error("Error al generar la representacion Impresa, ", e);
			throw e;
		}
		
		return comprobante.getUuid();
	}
	
	private void publicReporte(String reporte64, String uuid){
		
		ArchivoRepDTO pdf = new ArchivoRepDTO("PDF", uuid + ".pdf", "", MediaType.APPLICATION_PDF_VALUE, null);
		List<ArchivoRepDTO> archivos = new ArrayList<>();
		pdf.setContent(reporte64);
		archivos.add(pdf);
		this.archivoRepository.save(uuid, archivos);
	}

}
