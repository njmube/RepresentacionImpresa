package com.cubetech.representacion.impresa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cubetech.representacion.impresa.application.xml.comprobante.Comprobante;
import com.cubetech.representacion.impresa.domain.entidades.CuentaEmisor;
import com.cubetech.representacion.impresa.domain.entidades.Reporte;
import com.cubetech.representacion.impresa.domain.reporte.Detalle;
import com.cubetech.representacion.impresa.domain.reporte.Datos;
import com.cubetech.representacion.impresa.domain.reporte.Generales;
import com.cubetech.representacion.impresa.domain.reporte.Resumen;
import com.cubetech.representacion.impresa.domain.reporte.SubReporteQR;
import com.cubetech.representacion.impresa.domain.reporte.Totales;
import com.cubetech.representacion.impresa.domain.valueobjects.EmisorReporte;
import com.cubetech.representacion.impresa.domain.valueobjects.TemplateReporte;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ObjetosTest {
	
	public static JRBeanCollectionDataSource creaJRBeanColDataSource(ArrayList<?> array){
		return new JRBeanCollectionDataSource(array);
	}
	
	public static Detalle CreaDetalle(String producto, String cantidad, String unidad, String descripcion, String precio, String importe, String descuento){	  
		return new Detalle(producto, cantidad, unidad,descripcion,precio, importe, descuento);
	}
	public static Detalle CreaDetalle(){	  
		return  CreaDetalle("82121503","1", "H87 - Pieza", "IMPRESIÓN DIGITAL DE VINIL TAMAÑO 1.00 X 1.00 M", "100,000,000.00", "888,888,888.88", "444,444,444.44");
	}
	public static ArrayList<Detalle> creaConceptoList(){
		ArrayList<Detalle> ret = new ArrayList<Detalle>();
		
		ret.add(CreaDetalle("82121503","1", "H87 - Pieza", "Lonas Impresas", "100,000,000.00", "888,888,888.88", "444,444,444.44"));
		ret.add(CreaDetalle("82121503","2", "H87 - Pieza", "IMPRESIÓN DIGITAL DE VINIL TAMAÑO 1.00 X 1.00 M", "22,222,222.22", "3,333,333.33", null));
		ret.add(CreaDetalle("82121503","3", "H87 - Pieza", "Lonas Impresas", "100.00", "100.00", "777,777.77"));
		ret.add(CreaDetalle("82121503","4", "H87 - Pieza", "IMPRESIÓN DIGITAL DE VINIL TAMAÑO 1.00 X 1.00 M", "90.00", "270.00", null));
		ret.add(CreaDetalle("82121503","5", "H87 - Pieza", "Lonas Impresas", "10,000.00", "8,888.88", "44,444.44"));
		ret.add(CreaDetalle("82121503","6", "H87 - Pieza", "IMPRESIÓN DIGITAL DE VINIL TAMAÑO 1.00 X 1.00 M", "222.22", "33.33", null));
		ret.add(CreaDetalle("82121503","7", "H87 - Pieza", "Lonas Impresas", "100.00", "100.00", "7.77"));
		ret.add(CreaDetalle("82121503","8", "H87 - Pieza", "IMPRESIÓN DIGITAL DE VINIL TAMAÑO 1.00 X 1.00 M de tamaños variables", "90.00", "270.00", null));
		ret.add(CreaDetalle("82121503","9", "H87 - Pieza", "Lonas Impresas", "100,000,000.00", "0.88", "0.04"));
		ret.add(CreaDetalle("82121503","10", "H87 - Pieza", "IMPRESIÓN DIGITAL DE VINIL TAMAÑO 1.00 X 1.00 M", "1,234,567.89", "987,654,321.00", null));
		ret.add(CreaDetalle("82121503","150.25", "H87 - Pieza", "Lonas Impresas", "100.00", "100.00", "199.99"));
		ret.add(CreaDetalle("82121503","4000", "H87 - Pieza", "IMPRESIÓN DIGITAL DE VINIL TAMAÑO 1.00 X 1.00 M", "90.00", "270.00", null));
		
		return ret;
	}
	
	public static Resumen CreaResumen(String descripcion,String valor){
		return new Resumen(descripcion, valor);
	}
	public static Resumen CreaResumen(){
		return CreaResumen("Subtotal:", "$100.00");
	}
	public static ArrayList<Resumen> creaResumenList(){
		ArrayList<Resumen> ret= new ArrayList<>();
		
		ret.add(CreaResumen("Subtotal:", 	"$100.00"));
		ret.add(CreaResumen("IVA (16%):", "$16.00"));
		ret.add(CreaResumen("Total:", 		"$116.00"));
		
		return ret;
	}
	
	public static Totales CreaTotales(String importeLetra, String metodoPago, String formaPago, String condicones, JRBeanCollectionDataSource totalesDataSource){
		return new Totales(importeLetra, metodoPago, formaPago, condicones, totalesDataSource);
	}
	
	public static Totales CreaTotales(){
		return new Totales("DIECIOCHO MIL DOSCIENTOS VEINTIDOS PESOS 44/100 M.N.", 
				"PPD - Pago en parcialidades o diferido", "99 - Por definir", "UNAS CONDICONES DE PAGO ", 
				creaJRBeanColDataSource(creaResumenList()));
	}
	public static ArrayList<Totales> creaTotalesList(){
		ArrayList<Totales> ret = new ArrayList<>();
		ret.add(CreaTotales());
		return ret;
	}
	
	public static Generales CreaGenerales(String folio, String lugarFecha, String folioFiscal, String serieCertificado, 
														String tipoComprobante, String selloSat, String cadenaTimbreSat, String logo){
		
		return new Generales( folio,  lugarFecha,  folioFiscal,  serieCertificado, 
				 tipoComprobante,  selloSat,  cadenaTimbreSat,  logo);
	}
	public static Generales CreaGenerales(){
		return CreaGenerales("1",			"78398, 2017-11-22T17:23:12",			"5B7BBBCA-7942-4789-BF70-AF779ED3F795",
				"00001000000301793737",		"I - Ingresos", 
				"psXqlygT7/gS9vd3Fehl7edCWAOtOt9xvL7UQZCksawN/Xbc38fUBw+Ld7+BuJngjdOqjWReh9UD5JWNWN6xE1/FKZ+tq+BkI+lrlKhcNTwvcZBLpXqJ1jiEbYIrK9pZZgL8lMiqGs3BR/L2FicyVREsQhcdAKoYRKsTsJUAzgX7Ej9rttJkWekWzalWdd6mQD3EIousHr+7kqeW4/bXJJx0LleROp1IZUQO6318+6xlF0lo7fNP1SaytY/K0xuKAZtuMJQw217QASuHNxGbuRH40bp2b7N9KIKUC8L3sBDI9fklTGNILXlZLh/IptZgwT9xHkLlJsOANUYhJmk1iQ==",
				"||1.1|C8CCA896-0A11-42BC-B77D-C3886EE40FC9|2017-11-23T12:49:09|SST060807KU0|EUqP6FXjVM9mlWItuMp48to1vYKuTQQp/Ye58KN/g5f8gKfVuBxSUMTbWJTKRTPu0kx7mkM3sUGbWXwSX5Tl77JLLDjiqu5bs8PQ57uJ+QLxRKElzpF2o2pYP10j1jtpSvlkmn5T1N0V1AzhzUJVREt4Z0WQllV8lNLKgs/1wlNY67s+OtIXGH3QXkcnSrz1SH+TnD1PB0KEI8/xFsXP0K9rxs/1yUcxmXTE0PLCVhjXoiLQb3p9cSmcN4O3Sco6H2FHYptxjxCv//hkPpi/M1QibY4uHaxsnfZ7Xydak4zdIJI1LZ+92AfNnOdVbJOMWbufSFEf67Jm6hTyrOnm4A==|00001000000405179095||",
				//null);
				"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");
	}
	
	public static SubReporteQR CreaSubReporteQR(String codigoQR, String serieSAT, String fechaTimbre,	String proveedorRfc, String selloCFDI, String logoCube){
		return new SubReporteQR( codigoQR,  serieSAT,  fechaTimbre,	 proveedorRfc,  selloCFDI,  logoCube);
	}
	public static SubReporteQR CreaSubReporteQR(){
		return CreaSubReporteQR("https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx?id=5E80B3C8-DA66-4C94-A650-C4117C7DC667&re=CMC161114E10&rr=LAB110228D55&tt=21344.00&fe=cE5Kag==",
				"20001000000300022323",			"2017-11-22T17:30:59",			"SST060807KU0",
				"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");
	}
	
	public static Datos CreaDatos(String razonSocial, String rfc, 	 String regimen,	 String direccion,	 String ubicacion){
		return new Datos( razonSocial,  rfc, 	  regimen,	  direccion,	  ubicacion);
	}
	public static Datos CreaEmisor(){
		return CreaDatos("CUBE MANAGEMENT CONSULTING AND ENGINEERING SOLUTIONS SAS DE CV",
				"CMC161114E10","General de Ley Personas Morales","SAN DIEGO DE LOS PADRES #332 Col. VISTA ALEGRE SEGUNDA SECCION",
				"QUERETARO, QUERETARO 76090");
	}
	public static Datos CreaReceptor(){
		return CreaDatos("CLIENTE IMAGINARIO CUBETECH",
				"CIC850120PR6","","AV. SIEMPRE VIVA #123 A Col. TEQUISQUIAPAN","SAN LUIS POTOSI, SAN LUIS POTOSI 78290");
	}
	
	
	public static Map<String,Object> creaParametrosSubReporteQR(){
		Map<String,Object> ret = new HashMap<>();
		ret.put("datos", CreaSubReporteQR());
		return ret;
	}
	public static Map<String,Object> creaParametrosComprobante(){
		Map<String,Object> ret = new HashMap<>();
		
		ret.put("generales", CreaGenerales());
		ret.put("emisor", CreaEmisor());
		ret.put("receptor", CreaReceptor());
		ret.put("conceptos", creaJRBeanColDataSource(creaConceptoList()));
		ret.put("totales", creaJRBeanColDataSource(creaTotalesList()));
		ret.put("subReporteQR", creaParametrosSubReporteQR());
		
		return ret;
	}
	
	public static CuentaEmisor creaCuentaEmisor(){
		CuentaEmisor ret = new CuentaEmisor();
		ret.setEmisorReporte(creaEmisorReporte());
		ret.setReporte(creaReporte());
		return ret;
	}
	public static EmisorReporte creaEmisorReporte(){
		EmisorReporte ret = new EmisorReporte();
		ret.setCuenta("CUBE");
		ret.setEmisor("1");
		ret.setTipoComprobante("I");
		return ret;
	}
	public static Reporte creaReporte(){
		Reporte ret = new Reporte();
		
		ret.setActivo(true);
		ret.setUbicacion(creaUbicacion());
		return ret;
	}
	public static TemplateReporte creaUbicacion(){
		TemplateReporte ret = new TemplateReporte();
		ret.setUri("jasper/comprobante.jasper");
		return ret;
	}
	
///////////////////////////////////////////////////////////////////XML///////////////////////////////////////////////	
	public static Comprobante.Conceptos.Concepto creaConceptoXml(){
		Comprobante.Conceptos.Concepto ret = new Comprobante.Conceptos.Concepto();
		ret.setClaveProdServ("82121503");
		ret.setCantidad(new BigDecimal("150.25"));
		ret.setClaveUnidad("H87");
		ret.setUnidad("Pieza");
		ret.setDescripcion("IMPRESIÓN DIGITAL DE VINIL TAMAÑO 1.00 X 1.00 M de tamaños variables");
		ret.setValorUnitario(new BigDecimal("1234567.80"));
		ret.setImporte(new BigDecimal("1234.56789"));
		ret.setDescuento(new BigDecimal(".000001"));
		
		return ret;
	}
	
}
