package com.cubetech.representacion.impresa.application;

import com.cubetech.representacion.impresa.interfaces.dto.ImpresionDTO;

public interface ImpresionService {
	public String generaRepresentacionImpresa(String cuenta, ImpresionDTO impresion);
}
