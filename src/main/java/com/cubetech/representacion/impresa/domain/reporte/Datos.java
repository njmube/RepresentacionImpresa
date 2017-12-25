package com.cubetech.representacion.impresa.domain.reporte;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;

@Data
public class Datos implements ValueObject<Datos> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3216098702213508391L;
	private final String razonSocial;
	private final String rfc;
	private final String regimen;
	private final String direccion;
	private final String ubicacion;
	
	@Override
	public boolean sameValueAs(Datos other) {
		return this.equals(other);
	}
}
