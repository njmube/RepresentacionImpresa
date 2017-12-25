package com.cubetech.representacion.impresa.domain.valueobjects;

import javax.persistence.Transient;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude={"imagen"})
public class Logo implements ValueObject<Logo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -801820604145728220L;
	private String id;
	@Transient
	private String imagen;
	
	@Override
	public boolean sameValueAs(Logo other) {
		return this.equals(other);
	}

}
