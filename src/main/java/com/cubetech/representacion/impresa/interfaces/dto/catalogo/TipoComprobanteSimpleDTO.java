package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class TipoComprobanteSimpleDTO extends CatalogoDTO {

	private static final long serialVersionUID = 4083706287794327267L;
	private double valorMaximoImporte;
	String ValorMaximoClasificacion;
}
