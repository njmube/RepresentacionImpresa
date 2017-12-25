package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class PaisDTO extends CatalogoDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1712771975686851716L;
	String formatoCP;
	String formatoIdentidadTributaria;
}
