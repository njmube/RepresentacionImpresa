package com.cubetech.representacion.impresa.application.assembler;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cubetech.representacion.impresa.domain.entidades.ComprobanteEntidad;
import com.cubetech.representacion.impresa.domain.reporte.Datos;
import com.cubetech.representacion.impresa.domain.reporte.Detalle;
import com.cubetech.representacion.impresa.domain.reporte.Generales;
import com.cubetech.representacion.impresa.domain.reporte.Resumen;
import com.cubetech.representacion.impresa.domain.reporte.SubReporteQR;
import com.cubetech.representacion.impresa.domain.reporte.Totales;
import com.cubetech.representacion.impresa.domain.valueobjects.Concepto;
import com.cubetech.representacion.impresa.domain.valueobjects.Emisor;
import com.cubetech.representacion.impresa.domain.valueobjects.Importes;
import com.cubetech.representacion.impresa.domain.valueobjects.Impuesto;
import com.cubetech.representacion.impresa.domain.valueobjects.Receptor;
import com.cubetech.representacion.impresa.interfaces.dto.ArchivoRepDTO;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ImpresionAssembler {

	public Map<String, Object> toParametrosReporte(ComprobanteEntidad comprobante, ArchivoRepDTO logo){
		Map<String, Object> ret = new HashMap<>();
		DecimalFormat df = new DecimalFormat();
		
		
		df.setGroupingSize(3);
		df.setMaximumFractionDigits(6);
		df.setMinimumFractionDigits(comprobante.getMoneda().getDecimales());
		
		ret.put("generales", 		toGenerales(comprobante));
		ret.put("emisor", 			toDatos(comprobante.getEmisor()));
		ret.put("receptor", 		toDatos(comprobante.getReceptor()));
		ret.put("conceptos", 		toDetalles(comprobante.getConceptos(), df));
		ret.put("totales", 			toTotales(comprobante, df));
		ret.put("subReporteQR", toSubReporteQR(comprobante, logo));
		
		return ret;
	}

	private Generales toGenerales(ComprobanteEntidad comprobante){
		
		String logo = (comprobante.getEmisor().getLogo() != null) ? comprobante.getEmisor().getLogo().getImagen() : null;
		
		Generales ret = new Generales(comprobante.getSerieFolio(), 					comprobante.getLugarFecha(), 
										comprobante.getUuid(),															comprobante.getSerieCertificado(), 
										comprobante.getTipoComprobante().getContatenado(),  comprobante.getSelloSat(),
										comprobante.getCadenaComplementoSat(), 							logo);
		return ret;
	}
	private Datos toDatos(Emisor emisor){
		Datos ret = new Datos(emisor.getNombre(), 				 emisor.getRfc(), 	emisor.getRegimen().getContatenado(), 
								emisor.getResidencia().getDireccion(), emisor.getResidencia().getUbicacion());
		
		return ret;
	}
	private Datos toDatos(Receptor receptor){
		Datos ret = new Datos(receptor.getNombre(),  receptor.getRfc(), 	null, 
				receptor.getResidencia().getDireccion(), receptor.getResidencia().getUbicacion());
		return ret;
	}
	
	private JRBeanCollectionDataSource toDetalles(List<Concepto> conceptos, DecimalFormat df){
		JRBeanCollectionDataSource ret = null;
		List<Detalle> detalles;
		
		if(Utilerias.existeInfo(conceptos)){
			detalles = new ArrayList<>();
			for(Concepto tmp : conceptos){
				detalles.add(toDetalle(tmp,df));
			}
			ret = new JRBeanCollectionDataSource(detalles);
		}
		return ret;
	}
	private Detalle toDetalle(Concepto concepto, DecimalFormat df){
		Detalle ret = null;
		String descuento;
		if(concepto != null){
			descuento = (concepto.getDescuento() != null)? df.format(concepto.getDescuento()) : null;
			
			ret = new Detalle(concepto.getProducto().getClaveSat(),concepto.getCantidad().stripTrailingZeros().toPlainString(), concepto.getUnidad().getContatenado(), 
								concepto.getDescripcion(), df.format(concepto.getPrecio()), 
								df.format(concepto.getImporte()), descuento);
		}
		
		return ret;
	}
	
	private JRBeanCollectionDataSource toTotales(ComprobanteEntidad comprobante, DecimalFormat df){
		JRBeanCollectionDataSource ret = null;
		ArrayList<Totales> totales = new ArrayList<>();
		String formaPago 	= (comprobante.getFormaPago() != null)? comprobante.getFormaPago().getContatenado(): null ;
		String metodoPago = (comprobante.getMetodoPago() != null)? comprobante.getMetodoPago().getContatenado(): null ; 
		
		Totales total = new Totales(toTotalLetra(comprobante, df), metodoPago, 
				formaPago, comprobante.getCondicionesPago(), toResumenes(comprobante.getTotales(), df) );
		
		totales.add(total);
		ret = new JRBeanCollectionDataSource(totales);
		
		return ret;
	}
	
	private String toTotalLetra(ComprobanteEntidad comprobante, DecimalFormat df){
		String ret = "";
		BigDecimal total = comprobante.getTotales().getTotal();
		//BigInteger decimal = total.remainder(BigDecimal.ONE).movePointRight(total.scale()).abs().toBigInteger();
		//String decimales = decimal.toString() + "/" + BigDecimal.ONE.movePointRight(total.scale()).toString() + " ";
		String decimales = df.format(total);
		if(decimales.indexOf(".") >= 0){
			decimales = decimales.substring(decimales.indexOf(".") + 1) + "/" + BigDecimal.ONE.movePointRight(total.scale()).toString() + " ";
		}
		else 
			decimales = ""; 
		
		String moneda = "";
		String simboloDecimal = "";
		
		switch(comprobante.getMoneda().getClaveSat()){
		case "MXN":
				moneda = (total.compareTo(BigDecimal.ONE) > 0)? " PESOS, " : " PESO, ";
				simboloDecimal = "M.N.";
			break;
			default:
				break;
		}
		if(!comprobante.getTotalLetra().isEmpty())
			ret = comprobante.getTotalLetra() + moneda;
		ret = ret + decimales + simboloDecimal;
		
		return ret;
	}
	private JRBeanCollectionDataSource toResumenes(Importes totales, DecimalFormat df){
		JRBeanCollectionDataSource ret = null;
		List<Resumen> resumenes = new ArrayList<>();
		resumenes.add(new Resumen("SubTotal:", "$" + df.format(totales.getSubtotal())));
		
		if(Utilerias.existeInfo(totales.getImpuestosTotales())){
			for(Impuesto tmp : totales.getImpuestosTotales()){
				resumenes.add(new Resumen(tmp.getImpuesto().getDescripcion(), "$" + df.format(tmp.getValor())));
			}
		}
		resumenes.add(new Resumen("Total:", "$" + df.format(totales.getTotal())));
		
		ret = new JRBeanCollectionDataSource(resumenes);
		
		return ret;
	}
	private Map<String,Object> toSubReporteQR(ComprobanteEntidad comprobante, ArchivoRepDTO logo) {
		Map<String,Object> ret = new HashMap<>();
		SubReporteQR reporteQR =  new SubReporteQR(comprobante.codigoQR(), comprobante.getSerieCertificadoSat(), 
																				comprobante.getFechaTimbre(), comprobante.getRfcProveedor(),
																				comprobante.getSello(), logo.getContent());
		ret.put("datos", reporteQR);
		return ret;
	}
	
}
