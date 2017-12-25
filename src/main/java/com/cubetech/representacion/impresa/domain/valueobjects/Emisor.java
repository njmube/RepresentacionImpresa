package com.cubetech.representacion.impresa.domain.valueobjects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Transient;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;

@Data
public class Emisor implements ValueObject<Emisor> {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 8988964934114608217L;
	@Embedded
	@AttributeOverrides({
	    @AttributeOverride(name="direccion", column=@Column(name="EMISOR_DIRECCION")),
	    @AttributeOverride(name="ubicacion", column=@Column(name="EMISOR_UBICACION"))
	  })
	private Residencia residencia;
	@Embedded
	 @AttributeOverrides({
		 @AttributeOverride(name="id", column=@Column(name="LOGO_ID")),
	 })
	private Logo logo;
	
	@Transient
	private String rfc;
	@Transient
	private String nombre;
	@Transient
	private Catalogo regimen;

	@Override
	public boolean sameValueAs(Emisor other) {
		return this.equals(other);
	}

}
