package com.cubetech.representacion.impresa.application.assembler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.cubetech.representacion.impresa.application.xml.comprobante.CTipoFactor;
import com.cubetech.representacion.impresa.application.xml.comprobante.Comprobante;
import com.cubetech.representacion.impresa.domain.valueobjects.Catalogo;
import com.cubetech.representacion.impresa.domain.valueobjects.Importes;
import com.cubetech.representacion.impresa.domain.valueobjects.Impuesto;

public class TotalesAssembler {
	
	public Importes toImportes(Comprobante comprobante){
		Importes ret = new Importes();
		
		ret.setSubtotal(comprobante.getSubTotal());
		ret.setTotal(comprobante.getTotal());
		ret.setImpuestosTotales(toImpuestos(comprobante.getImpuestos()));
		
		return ret;
	}
	
	private List<Impuesto> toImpuestos(Comprobante.Impuestos impuestosXml){
		List<Impuesto> ret = null;
		
		if((impuestosXml.getTraslados() != null && Utilerias.existeInfo(impuestosXml.getTraslados().getTraslado())) || 
				(impuestosXml.getRetenciones()!= null && Utilerias.existeInfo(impuestosXml.getRetenciones().getRetencion())) ){
			ret = new ArrayList<>();
			if(impuestosXml.getTraslados() != null && Utilerias.existeInfo(impuestosXml.getTraslados().getTraslado()))
				for(Comprobante.Impuestos.Traslados.Traslado tmp : impuestosXml.getTraslados().getTraslado()){
					ret.add(toImpuesto(tmp));
				}
			if(impuestosXml.getRetenciones()!= null && Utilerias.existeInfo(impuestosXml.getRetenciones().getRetencion()))
				for(Comprobante.Impuestos.Retenciones.Retencion tmp: impuestosXml.getRetenciones().getRetencion()){
					ret.add(toImpuesto(tmp));
				}
		}
		
		return ret;
	}
	
	private Impuesto toImpuesto(Comprobante.Impuestos.Traslados.Traslado traslado){
		Impuesto ret = null;
		DecimalFormat df = new DecimalFormat("######.####%");
		Catalogo impuesto;
		
		String descripcion = "";
		if(traslado != null){
			ret = new Impuesto();
			ret.setValor(traslado.getImporte());
			if(traslado.getTipoFactor() == CTipoFactor.TASA){
				descripcion = " (" + df.format(traslado.getTasaOCuota()) + "):";
			}//Hace falta el esle de cuota.
			impuesto = new Catalogo(traslado.getImpuesto());
			impuesto.setDescripcion(descripcion);
			ret.setImpuesto(impuesto);
		}
		
		return ret;
	}
	private Impuesto toImpuesto(Comprobante.Impuestos.Retenciones.Retencion retencion){
		Impuesto ret = null;
		Catalogo impuesto;
		
		if(retencion != null){
			ret = new Impuesto();
			ret.setValor(retencion.getImporte());
			//ret.setDescripcion(retencion.getImpuesto() + " Retenido:");
			impuesto = new Catalogo(retencion.getImpuesto());
			impuesto.setDescripcion(" Retenido:");
			ret.setImpuesto(impuesto);
		}
		
		return ret;
	}
	
}
