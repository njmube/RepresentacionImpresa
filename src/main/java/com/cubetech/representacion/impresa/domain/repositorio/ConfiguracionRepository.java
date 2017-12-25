package com.cubetech.representacion.impresa.domain.repositorio;

import org.springframework.data.repository.Repository;

import com.cubetech.representacion.impresa.domain.entidades.Configuracion;

public interface ConfiguracionRepository extends Repository<Configuracion, String> {
	public Configuracion findOne(String llave);
}
