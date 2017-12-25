package com.cubetech.representacion.impresa.domain.repositorio;

import org.springframework.data.repository.Repository;

import com.cubetech.representacion.impresa.domain.entidades.Reporte;

public interface ReporteRepository extends Repository<Reporte, Long> {
	public Reporte findById(long id);
}
