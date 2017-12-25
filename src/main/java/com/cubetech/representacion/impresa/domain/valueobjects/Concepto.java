package com.cubetech.representacion.impresa.domain.valueobjects;

import java.math.BigDecimal;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;

@Data
public class Concepto implements ValueObject<Concepto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4201604836166081732L;
	private Catalogo producto;
	private BigDecimal cantidad;
	private Catalogo unidad;
	private String descripcion;
	private BigDecimal precio;
	private BigDecimal importe;
	private BigDecimal descuento;
	
	@Override
	public boolean sameValueAs(Concepto other) {
		
		return this.equals(other);
	}

}
