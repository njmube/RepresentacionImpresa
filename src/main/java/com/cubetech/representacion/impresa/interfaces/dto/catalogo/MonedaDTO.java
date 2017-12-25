package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class MonedaDTO extends CatalogoDTO {

	private static final long serialVersionUID = -8543557405151474766L;
	int decimales;
	double variacion;
}
