package com.cubetech.representacion.impresa.domain.valueobjects;

import java.math.BigDecimal;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;

@Data
public class Impuesto implements ValueObject<Impuesto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4502597943616394563L;
	private Catalogo impuesto;
	private BigDecimal valor;
	
	@Override
	public boolean sameValueAs(Impuesto other) {
		return this.equals(other);
	}

}
