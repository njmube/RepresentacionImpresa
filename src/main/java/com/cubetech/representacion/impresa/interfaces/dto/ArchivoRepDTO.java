package com.cubetech.representacion.impresa.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchivoRepDTO {
	private String correlacion;
	private String nombre;
	private String content;
	private String tipo;
	private String id;
}
