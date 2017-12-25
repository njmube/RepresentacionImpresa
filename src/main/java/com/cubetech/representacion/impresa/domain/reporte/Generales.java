package com.cubetech.representacion.impresa.domain.reporte;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

import com.cubetech.representacion.impresa.application.assembler.Utilerias;
import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;

@Data
public class Generales implements ValueObject<Generales>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5888168541469035498L;
	private final String serieFolio;
	private final String fecha;
	private final String folioFiscal;
	private final String serieCSD;
	private final String tipoComprobante;
	private final String selloSAT;
	private final String cadenaTimbreSAT;
	private final String logo;
	
	public InputStream getLogo(){
		if(Utilerias.existeInfo(logo)){
			byte[] asBytes = Base64.getDecoder().decode(this.logo);
			return new ByteArrayInputStream(asBytes);
		}
		else return null;
	}

	@Override
	public boolean sameValueAs(Generales other) {
		return this.equals(other);
	}
}
