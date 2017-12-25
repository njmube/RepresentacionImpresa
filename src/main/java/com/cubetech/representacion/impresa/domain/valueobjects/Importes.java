package com.cubetech.representacion.impresa.domain.valueobjects;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cubetech.representacion.impresa.application.assembler.Utilerias;
import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;

@Data
public class Importes implements ValueObject<Importes> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1402819047810794001L;
	private BigDecimal subtotal;
	private List<Impuesto> impuestosTotales;
	private BigDecimal total;
	
	@Override
	public boolean sameValueAs(Importes other) {
		
		return this.equals(other);
	}

	public Map<String, String> consultaClavesCatalogos() {
		Map<String, String> ret = null;
		Set<String> aux = null;
		
		if(Utilerias.existeInfo(this.impuestosTotales)){
			ret = new HashMap<>();
			aux = new HashSet<>();
			for(Impuesto tmp : this.impuestosTotales){
				if(!aux.contains(tmp.getImpuesto().getClaveSat())){
					aux.add(tmp.getImpuesto().getClaveSat());
					ret.put("impuesto", tmp.getImpuesto().getClaveSat());
				}
			}
		}
		
		return ret;
	}

}
