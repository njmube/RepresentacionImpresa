package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class ImpuestoDTO extends CatalogoDTO{
	
	private static final long serialVersionUID = -4664408642299151218L;
	private boolean retencion;
	private boolean traslado;
	private String impuestoTipo;
	private String entidad;
	
}
