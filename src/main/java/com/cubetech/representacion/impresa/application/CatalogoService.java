package com.cubetech.representacion.impresa.application;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cubetech.representacion.impresa.interfaces.dto.catalogo.CatalogoDTO;


public interface CatalogoService {
	public Map<String, CatalogoDTO> consultaCatalogos(List<Map<String,String>> claves, Date fecha);
}
