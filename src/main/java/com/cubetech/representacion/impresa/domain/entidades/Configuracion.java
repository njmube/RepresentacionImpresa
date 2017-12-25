package com.cubetech.representacion.impresa.domain.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.cubetech.representacion.impresa.domain.Entidad;

import lombok.Data;

@Data
@Entity
public class Configuracion implements Entidad<Configuracion> {

	@Id
	private String llave;
	private String valor;
	
	@Override
	public boolean sameIdentityAs(Configuracion other) {
		// TODO Auto-generated method stub
		return false;
	}

}
