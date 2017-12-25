package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=false)
public abstract class CatalogoDTO implements Serializable {
	
	private static final long serialVersionUID = 1135275155269013493L;
	
	private String claveSat;
	private String descripcion;
	private boolean vigente;
	
	public CatalogoDTO(){
		
	}
	public CatalogoDTO(CatalogoDTO other){
		this.claveSat = other.claveSat;
		this.descripcion = other.descripcion;
		this.vigente = other.vigente;
	}

}
