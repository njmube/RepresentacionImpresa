package com.cubetech.representacion.impresa.interfaces.restclient;

import com.cubetech.representacion.impresa.application.error.ConexionException;
import com.cubetech.representacion.impresa.interfaces.dto.EmisorDTO;

public interface EmisorRepository {
	public EmisorDTO consultaEmisor(String cuenta, String correlacion) throws ConexionException;
}
