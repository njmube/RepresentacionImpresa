package com.cubetech.representacion.impresa.domain.valueobjects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Transient;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;

@Data
public class Receptor implements ValueObject<Receptor>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7266372959793469143L;

	@Transient
	private String rfc;
	@Transient
	private String nombre;
	@Transient
	private Catalogo uso;
	
	@Embedded
	 @AttributeOverrides({
	    @AttributeOverride(name="direccion", column=@Column(name="RECEPTOR_DIRECCION")),
	    @AttributeOverride(name="ubicacion", column=@Column(name="RECEPTOR_UBICACION"))
	  })
	private Residencia residencia;

	@Override
	public boolean sameValueAs(Receptor other) {
		return this.equals(other);
	}
}
