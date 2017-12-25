package com.cubetech.representacion.impresa.application.assembler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cubetech.representacion.impresa.application.error.InternalException;

public class Utilerias {
	
	private final static Logger logger = LoggerFactory.getLogger(Utilerias.class);
	
	public static boolean existeInfo(String valor){
		return valor != null && !valor.trim().isEmpty();
	}
	public static boolean existeInfo(List<?> lista){
		return lista != null && !lista.isEmpty();
	}
	public static boolean existeInfo(Map<?,?> map){
		return map != null && !map.isEmpty();
	}
	public static XMLGregorianCalendar toXMLGregorianCalendar(LocalDateTime fechaHora ) throws InternalException {
		try {
			DateTimeFormatter formatter  = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			String fecha = fechaHora.withNano(0).format(formatter);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(fecha);
		} catch (DatatypeConfigurationException e) {
			logger.error("Conversion a XMLGregorianCalendar" , e);
			throw new InternalException("Error en la conversion a XMLGregorianCalendar", e.getCause());
		}
	}
	public static LocalDateTime toLocalDateTime(XMLGregorianCalendar xmlfechaHora){
		LocalDateTime ret = null;
		ret = xmlfechaHora.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
		return ret;
	}
	
}
