package com.cubetech.representacion.impresa.domain.entidades;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.cubetech.representacion.impresa.domain.Entidad;
import com.cubetech.representacion.impresa.domain.valueobjects.EmisorReporte;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(exclude={"reporte"})
@Data
public class CuentaEmisor implements Entidad<CuentaEmisor> {
	@Embedded
	@EmbeddedId
	private EmisorReporte emisorReporte;
	@ManyToOne(optional=false)
	private Reporte reporte;
	
	@Override
	public boolean sameIdentityAs(CuentaEmisor other) {
		return this.equals(other);
	}
}
