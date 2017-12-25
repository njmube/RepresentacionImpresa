package com.cubetech.representacion.impresa.domain.valueobjects;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmisorReporte implements ValueObject<EmisorReporte> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 350749417814214284L;
	private String cuenta;
	private String emisor;
	private String tipoComprobante;

	@Override
	public boolean sameValueAs(EmisorReporte other) {
		return this.equals(other);
	}

}
