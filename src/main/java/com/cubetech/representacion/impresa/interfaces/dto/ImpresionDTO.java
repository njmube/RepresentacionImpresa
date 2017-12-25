package com.cubetech.representacion.impresa.interfaces.dto;

import lombok.Data;

@Data
public class ImpresionDTO {
	private String emisor;
	private String comprobante;
	private DireccionDTO receptor;
}
