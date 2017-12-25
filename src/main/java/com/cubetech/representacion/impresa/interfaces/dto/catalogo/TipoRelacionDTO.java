package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class TipoRelacionDTO extends CatalogoDTO {
	
	private static final long serialVersionUID = 1743361590393669200L;

}
