package com.cubetech.representacion.impresa.application;



import com.cubetech.representacion.impresa.domain.entidades.Reporte;

public interface CuentaEmisorService {
	public Reporte ObtenReporte(String cuenta, String emisor, String tipoComprobante);
}
