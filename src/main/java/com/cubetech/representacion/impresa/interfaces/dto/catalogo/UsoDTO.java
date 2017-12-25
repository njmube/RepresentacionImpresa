package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class UsoDTO extends CatalogoDTO {

	private static final long serialVersionUID = 1677743924288304810L;
	private boolean fisica;
	private boolean moral;

}
