package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class RegimenFiscalDTO extends CatalogoDTO {
	
	private static final long serialVersionUID = -7470452102140107911L;
	private boolean fisica;
	private boolean moral;
}
