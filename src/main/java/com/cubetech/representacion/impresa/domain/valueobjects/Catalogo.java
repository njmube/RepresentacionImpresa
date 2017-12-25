package com.cubetech.representacion.impresa.domain.valueobjects;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude={"descripcion"})
public class Catalogo implements ValueObject<Catalogo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2768945032402864913L;
	private final String claveSat;
	private String descripcion;
	
	public Catalogo(String claveSat){
		if (claveSat == null) throw new NullPointerException("claveSat");
		this.claveSat = claveSat;
		this.descripcion = "";
	}
	
	public String getContatenado(){
		return this.claveSat + " - " + this.descripcion;
	}
	
	@Override
	public boolean sameValueAs(Catalogo other) {
		return this.equals(other);
	}

}
