package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class MetodoPagoDTO extends CatalogoDTO {
	
	private static final long serialVersionUID = -3770435485391586149L;

}
