package com.cubetech.representacion.impresa.application;

import com.cubetech.representacion.impresa.domain.valueobjects.Emisor;

public interface EmisorService {
	public Emisor consultaInfoEmisor(String cuenta, String emisor, String lugarExpedicion); 
}
