package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class ProductoServicioDTO extends CatalogoDTO {

	private static final long serialVersionUID = -5530308336613222968L;
	String trasladoIva;
	String trasladoIeps;
	String complementoRequerido;
	String complementoSeleccion;

}
