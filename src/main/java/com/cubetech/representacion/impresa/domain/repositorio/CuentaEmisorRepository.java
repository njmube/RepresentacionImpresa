package com.cubetech.representacion.impresa.domain.repositorio;

import org.springframework.data.repository.Repository;

import com.cubetech.representacion.impresa.domain.entidades.CuentaEmisor;
import com.cubetech.representacion.impresa.domain.valueobjects.EmisorReporte;


public interface CuentaEmisorRepository extends Repository<CuentaEmisor, EmisorReporte> {
	public CuentaEmisor save(CuentaEmisor ctaEmisor);
	public CuentaEmisor findOne(EmisorReporte er);
}
