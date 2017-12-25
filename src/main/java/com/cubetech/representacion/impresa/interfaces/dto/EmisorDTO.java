package com.cubetech.representacion.impresa.interfaces.dto;

import lombok.Data;

@Data
public class EmisorDTO {
	private String calle;
	private String numeroExterior;
	private String numeroInterior;
	private String codigoPostal;
	private String colonia;
	private String pais;
	private String estado;
	private String municipio;
	private String logo;
}
