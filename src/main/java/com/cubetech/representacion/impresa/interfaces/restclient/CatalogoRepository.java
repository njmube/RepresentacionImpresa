package com.cubetech.representacion.impresa.interfaces.restclient;

import java.util.Map;

import com.cubetech.representacion.impresa.interfaces.dto.catalogo.CatalogoDTO;


public interface CatalogoRepository {
	public Map<String, CatalogoDTO> consultaClaves(Map<String,String> claves);
}
