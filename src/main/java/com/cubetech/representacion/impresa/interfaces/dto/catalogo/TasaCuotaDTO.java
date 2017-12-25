package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;

@Data
public class TasaCuotaDTO {
	private String tipo;
	private Double minimo;
	private Double valor;
	private ImpuestoDTO impuesto;
	private TipoFactorDTO factor;
	private boolean traslado;
	private boolean retencion;
	private boolean vigente;
}
