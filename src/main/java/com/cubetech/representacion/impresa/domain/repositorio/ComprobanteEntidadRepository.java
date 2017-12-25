package com.cubetech.representacion.impresa.domain.repositorio;

import org.springframework.data.repository.Repository;

import com.cubetech.representacion.impresa.domain.entidades.ComprobanteEntidad;

public interface ComprobanteEntidadRepository extends Repository<ComprobanteEntidad, String> {
	public ComprobanteEntidad save(ComprobanteEntidad ce);
	public ComprobanteEntidad findOne(String uuid);
}
