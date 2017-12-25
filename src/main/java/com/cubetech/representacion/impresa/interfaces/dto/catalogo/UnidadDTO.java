package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class UnidadDTO extends CatalogoDTO {

	private static final long serialVersionUID = -1697104383222313874L;
	String simbolo;
}
