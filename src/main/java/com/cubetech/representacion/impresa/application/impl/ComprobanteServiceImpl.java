package com.cubetech.representacion.impresa.application.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubetech.representacion.impresa.application.CatalogoService;
import com.cubetech.representacion.impresa.application.ComprobanteService;
import com.cubetech.representacion.impresa.application.ComprobanteXmlService;
import com.cubetech.representacion.impresa.application.CuentaEmisorService;
import com.cubetech.representacion.impresa.application.EmisorService;
import com.cubetech.representacion.impresa.application.assembler.ComprobanteAssembler;
import com.cubetech.representacion.impresa.application.xml.comprobante.Comprobante;
import com.cubetech.representacion.impresa.application.xml.timbre.TimbreFiscalDigital;
import com.cubetech.representacion.impresa.domain.entidades.ComprobanteEntidad;
import com.cubetech.representacion.impresa.domain.valueobjects.Emisor;
import com.cubetech.representacion.impresa.interfaces.dto.DireccionDTO;
import com.cubetech.representacion.impresa.interfaces.dto.catalogo.CatalogoDTO;

@Service
public class ComprobanteServiceImpl implements ComprobanteService {

	private ComprobanteXmlService comprobanteXmlService;
	private ComprobanteAssembler comprobanteAssembler;
	private EmisorService emisorService;
	private CatalogoService catalogoService;
	private CuentaEmisorService cuentaEmisorService;

	
	@Autowired
	public ComprobanteServiceImpl(ComprobanteXmlService comprobanteXmlService, EmisorService emisorService,
															CatalogoService catalogoService, CuentaEmisorService cuentaEmisorService){
		this.comprobanteXmlService 	= comprobanteXmlService;
		this.emisorService 					= emisorService;
		this.comprobanteAssembler 	= new ComprobanteAssembler();
		this.cuentaEmisorService		= cuentaEmisorService;
		this.catalogoService 				= catalogoService;
	}
	
	@Override
	public ComprobanteEntidad generaComprobanteEntidad(String comprobanteStr, String cuenta, 
																										String emisorCorrelacion, DireccionDTO receptorDir) {
		Comprobante comprobante;
		ComprobanteEntidad ret = null;
		TimbreFiscalDigital timbre;
		String cadenaOriginalTFD;
		Emisor emisor;
		List<Map<String,String>> clavesCatalogo;
		Map<String, CatalogoDTO> catalogosDto;
		
		comprobante = this.comprobanteXmlService.generaComprobante(comprobanteStr);
		timbre =  comprobanteXmlService.obtenerTimbre(comprobante);
		cadenaOriginalTFD = comprobanteXmlService.cadenaOriginalTFD(timbre);
		emisor = this.emisorService.consultaInfoEmisor(cuenta, emisorCorrelacion, comprobante.getLugarExpedicion());
		ret = this.comprobanteAssembler.toComprobanteEntidad(comprobante, timbre, emisor, cadenaOriginalTFD, receptorDir);
		ret.setReporte(this.cuentaEmisorService.ObtenReporte(cuenta, emisorCorrelacion, ret.getTipoComprobante().getClaveSat()));
		clavesCatalogo = ret.consultaClavesCatalogos();
		catalogosDto = this.catalogoService.consultaCatalogos(clavesCatalogo, comprobante.getFecha().toGregorianCalendar().getTime());
		this.comprobanteAssembler.asignaCatalogos(ret,catalogosDto);
		
		return ret;
	}

}
