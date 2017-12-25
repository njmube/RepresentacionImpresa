package com.cubetech.representacion.impresa.domain.valueobjects;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;

@Data
public class Residencia implements ValueObject<Residencia> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3333731313670535165L;
	private String direccion;
	private String ubicacion;
	
	@Override
	public boolean sameValueAs(Residencia other) {
		
		return this.equals(other);
	}

}
