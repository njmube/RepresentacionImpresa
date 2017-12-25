package com.cubetech.representacion.impresa.domain.valueobjects;

import com.cubetech.representacion.impresa.domain.ValueObject;

import lombok.Data;

@Data
public class TemplateReporte implements ValueObject<TemplateReporte> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8825594107427338899L;
	private String uri;

	@Override
	public boolean sameValueAs(TemplateReporte other) {
		// TODO Auto-generated method stub
		return false;
	}

}
