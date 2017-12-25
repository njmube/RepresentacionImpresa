package com.cubetech.representacion.impresa.application.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubetech.representacion.impresa.application.CatalogoService;
import com.cubetech.representacion.impresa.interfaces.dto.catalogo.CatalogoDTO;
import com.cubetech.representacion.impresa.interfaces.restclient.CatalogoRepository;


@Service
public class CatalogoServiceImpl implements CatalogoService {

	private CatalogoRepository catalogoRepository;
	
	@Autowired
	public CatalogoServiceImpl(CatalogoRepository catalogoRepository){
		this.catalogoRepository = catalogoRepository;
		
	}
	
	@Override
	public Map<String, CatalogoDTO> consultaCatalogos(List<Map<String, String>> claves, Date fecha) {
		Map<String, CatalogoDTO> ret= null;
		Map<String, CatalogoDTO> tmp;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String parametroFecha = sdf.format(fecha);
		
		List<Map<String, String>> noRepetidos = reduce(claves);
		for(Map<String, String> claveTmp : noRepetidos){
			claveTmp.put("fecha", parametroFecha);
			tmp = catalogoRepository.consultaClaves(claveTmp);
			if(ret == null)
				ret = tmp;
			else{
				for (Map.Entry<String, CatalogoDTO> entry : tmp.entrySet()){
					if(!ret.containsKey(entry.getKey()))
						ret.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return ret;
	}
	
	private List<Map<String, String>> reduce(List<Map<String, String>> claves){
		return claves;
	}
	
	/*
	 * Hace falta probar el algoritmo
	 * 
	 */
	/*private List<Map<String, String>> reduce(List<Map<String, String>> claves){
		Map<String,String> tmp;
		List<Map<String, String>> ret = new ArrayList<>();
		boolean band =false;
		
		for(Map<String,String> mapa : claves){
			for (Map.Entry<String, String> entry : mapa.entrySet()){
				tmp = null;
				band = true;
				for(int i=0 ; i < ret.size() && band && tmp == null ; i++){
					if(!ret.get(i).containsKey(entry.getKey())){
						tmp = ret.get(i);
					}else{
						if(ret.get(i).containsValue(entry.getValue())){
							band = false;
						}
					}
				}
				if(band){
					if(tmp == null){
						tmp = new HashMap<>();
						ret.add(tmp);
					}
					tmp.put(entry.getKey(), entry.getValue());
				}
			}
		}
		
		return ret;
	}*/
	
	/*
	 * Segundo metodo son 2 apuntaroes uno al final y 1 al incia reduciendo la lista del final al inicio.
	 * 
	 */

}
