package com.cubetech.representacion.impresa.domain.reporte;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;

@Data
public class SubReporteQR implements ValueObject<SubReporteQR>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3144584005866254014L;
	private final String codigoQR;
	private final String serieSAT;
	private final String fechaTimbre;
	private final String proveedorRfc;
	private final String selloCFDI;
	private final String logoCube;
	
	public InputStream getLogoCube(){
		byte[] asBytes = Base64.getDecoder().decode(this.logoCube);
		return new ByteArrayInputStream(asBytes);
	}

	@Override
	public boolean sameValueAs(SubReporteQR other) {
		return this.equals(other);
	}
}
