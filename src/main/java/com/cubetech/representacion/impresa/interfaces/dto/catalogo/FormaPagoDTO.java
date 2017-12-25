package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true, includeFieldNames=false)
public class FormaPagoDTO extends CatalogoDTO{
	
	private static final long serialVersionUID = 759112318083090947L;
	private String bancarizado;
	private String numeroOperacion;
	private String ordenanteRfc;
	private String ordenanteCuenta;
	private String ordenantePatron;
	private String beneficiarioRfc;
	private String beneficiarioCuenta;
	private String beneficiarioPatron;
	private String tipoCadenaPago;
	private String bancoEmisor;

}
