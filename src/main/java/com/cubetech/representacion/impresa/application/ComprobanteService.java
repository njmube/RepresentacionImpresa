package com.cubetech.representacion.impresa.application;

import com.cubetech.representacion.impresa.domain.entidades.ComprobanteEntidad;
import com.cubetech.representacion.impresa.interfaces.dto.DireccionDTO;

public interface ComprobanteService {
	public ComprobanteEntidad generaComprobanteEntidad(String comprobante, String cuenta, String emisorCorrelacion, DireccionDTO receptorDir);
}
