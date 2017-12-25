package com.cubetech.representacion.impresa.domain.reporte;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;

@Data
public class Resumen implements ValueObject<Resumen>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7277711382346041664L;
	private final String descripcion;
	private final String importe;
	
	@Override
	public boolean sameValueAs(Resumen other) {
		return this.equals(other);
	}
}
