package com.cubetech.representacion.impresa.domain.reporte;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Data
public class Totales implements ValueObject<Totales>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2939844823291585196L;
	private final String totalLetra;
	private final String metodoPago;
	private final String formaPago;
	private final String condicionesPago;
	private final JRBeanCollectionDataSource resumenDataSource;
	
	@Override
	public boolean sameValueAs(Totales other) {
		return this.equals(other);
	}
}
