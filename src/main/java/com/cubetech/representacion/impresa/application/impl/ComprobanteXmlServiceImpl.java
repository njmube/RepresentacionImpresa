package com.cubetech.representacion.impresa.application.impl;

import java.io.StringReader;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubetech.representacion.impresa.application.ComprobanteXmlService;
import com.cubetech.representacion.impresa.application.error.InternalException;
import com.cubetech.representacion.impresa.application.xml.comprobante.Comprobante;
import com.cubetech.representacion.impresa.application.xml.timbre.TimbreFiscalDigital;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ComprobanteXmlServiceImpl implements ComprobanteXmlService {
	
	private JAXBContext jaxbContext;
	private Templates templatesCadena;
	
	@Autowired
	public ComprobanteXmlServiceImpl(JAXBContext jaxbContext){
		this.jaxbContext =jaxbContext;
	}
	
	@Resource(name="templateCadenaTimbre")
	protected void setTemplatesCadena(Templates templatesCadena){
		this.templatesCadena = templatesCadena;
	}

	@Override
	public String cadenaOriginalTFD(TimbreFiscalDigital timbre) {
		String ret = null;
		JAXBSource source;
		Transformer transformer;
		StringWriter sw = new StringWriter();
		
		try {
			source = new JAXBSource(this.jaxbContext, timbre);
			transformer = this.templatesCadena.newTransformer();
			transformer.transform(source,new StreamResult(sw));
		  ret = sw.toString();
		} catch (TransformerException e) {
			log.error("Error al crear el transformer para la cadena Origial ", e);
			throw new InternalException("Error al crear el transformer para la cadena Origial ", e.getCause());
		} catch (JAXBException e) {
			log.error("Error al crear el source para el JAXB ", e);
			throw new InternalException("Error al crear el source para el JAXB ", e.getCause());
		}
		
		if(log.isDebugEnabled())
			log.debug("CadenaOriginal: {}" ,ret);
		
		return ret;
	}

	@Override
	public TimbreFiscalDigital obtenerTimbre(Comprobante comprobante) {
		TimbreFiscalDigital ret= null;
		ret = (TimbreFiscalDigital)this.obtenComplemento(comprobante, TimbreFiscalDigital.class);
		
		return ret;
	}

	@Override
	public Comprobante generaComprobante(String xml) {
		Comprobante ret = null;
		Unmarshaller u; 
		StringBuffer xmlStr;
		try {
			u = this.jaxbContext.createUnmarshaller();
			xmlStr = new StringBuffer(xml);
			ret = (Comprobante)u.unmarshal( new StreamSource(new StringReader(xmlStr.toString())));
		} catch (JAXBException e) {
			log.error("Error al crear el unmarshaler comprobante:{} ", xml , e);
			throw new InternalException("Error al crear el unmarshaler", e.getCause());
		}
		return ret;
	}
	
	private Object obtenComplemento(Comprobante comprobante, Class<?> clase) {
		Object ret = null;
		
		for (int i=0 ; i < comprobante.getComplemento().get(0).getAny().size() ; i++){
			if(comprobante.getComplemento().get(0).getAny().get(i).getClass() == clase ){
				ret =  comprobante.getComplemento().get(0).getAny().get(i);
				break;
			}
		}
		return ret;
	}
	
}
