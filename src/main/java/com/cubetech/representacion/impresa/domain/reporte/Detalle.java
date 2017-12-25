package com.cubetech.representacion.impresa.domain.reporte;


import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;

@Data
public class Detalle implements ValueObject<Detalle>{

	private static final long serialVersionUID = -3814795862995210974L;
	private final String producto;
	private final String cantidad;
	private final String unidad;
	private final String descripcion;
	private final String precio;
	private final String importe;
	private final String descuento;
	
	@Override
	public boolean sameValueAs(Detalle other) {
		return this.equals(other);
	}
}
