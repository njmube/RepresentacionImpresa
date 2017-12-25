package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class CodigoPostalDTO extends CatalogoDTO {
	
	private static final long serialVersionUID = 2992277011728580135L;
	
	private String estado;
	private String municipio;
	private String localidad;
	
}
