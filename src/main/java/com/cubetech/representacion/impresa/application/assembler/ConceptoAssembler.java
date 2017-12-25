package com.cubetech.representacion.impresa.application.assembler;

import com.cubetech.representacion.impresa.application.xml.comprobante.Comprobante;
import com.cubetech.representacion.impresa.domain.valueobjects.Catalogo;
import com.cubetech.representacion.impresa.domain.valueobjects.Concepto;

public class ConceptoAssembler {
	public Concepto toConcepto(Comprobante.Conceptos.Concepto conceptoXml){
		Concepto ret = null;
		Catalogo aux;
		
		if(conceptoXml != null){
			ret = new Concepto();
			ret.setProducto(new Catalogo(conceptoXml.getClaveProdServ()));
			ret.setCantidad(conceptoXml.getCantidad());
			aux = new Catalogo(conceptoXml.getClaveUnidad());
			if(Utilerias.existeInfo(conceptoXml.getUnidad()))
				aux.setDescripcion(conceptoXml.getUnidad());
			ret.setUnidad(aux);
			ret.setDescripcion(conceptoXml.getDescripcion());
			ret.setPrecio(conceptoXml.getValorUnitario());
			ret.setImporte(conceptoXml.getImporte());
			if(conceptoXml.getDescuento() != null)
				ret.setDescuento(conceptoXml.getDescuento());
		}
		
		return ret;
	}
}
