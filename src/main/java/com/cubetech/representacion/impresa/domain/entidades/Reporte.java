package com.cubetech.representacion.impresa.domain.entidades;

import java.io.InputStream;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.cubetech.representacion.impresa.domain.Entidad;
import com.cubetech.representacion.impresa.domain.valueobjects.TemplateReporte;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(exclude={"id"})
@Entity
public class Reporte implements Entidad<Reporte> {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="uri", column=@Column(nullable= false, unique=true))
	})
	private TemplateReporte ubicacion;
	private boolean activo;
	
	@Transient
	private InputStream reporte;
	

	@Override
	public boolean sameIdentityAs(Reporte other) {
		return this.equals(other);
	}

}
