package com.cubetech.representacion.impresa.interfaces.restclient;

import java.util.List;

import com.cubetech.representacion.impresa.application.error.ConexionException;
import com.cubetech.representacion.impresa.interfaces.dto.ArchivoRepDTO;


public interface ArchivoRepository {

	public ArchivoRepDTO findbyCuentaCorrelacion(String cuenta, String correlation) throws ConexionException;
	public List<ArchivoRepDTO> save(String cuenta, List<ArchivoRepDTO> archivo);
	
}
