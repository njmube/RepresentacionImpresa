package com.cubetech.representacion.impresa.application.assembler;

import com.cubetech.representacion.impresa.domain.valueobjects.Emisor;
import com.cubetech.representacion.impresa.domain.valueobjects.Logo;
import com.cubetech.representacion.impresa.domain.valueobjects.Residencia;
import com.cubetech.representacion.impresa.interfaces.dto.ArchivoRepDTO;
import com.cubetech.representacion.impresa.interfaces.dto.EmisorDTO;


public class EmisorAssembler {
	public Emisor toEmisor(EmisorDTO emisorDto, ArchivoRepDTO archivo, String lugarExpedicion){
		Emisor ret = null;
		
		if(emisorDto != null){
			ret = new Emisor();
			ret.setResidencia(this.toResidencia(emisorDto, lugarExpedicion));
			ret.setLogo(this.toLogo(archivo));
		}
		return ret;
	}
	private Residencia toResidencia(EmisorDTO emisorDto, String lugarExpedicion){
		Residencia ret = null;
		String direccion = null;
		String ubicacion = null;
		
		if(Utilerias.existeInfo(emisorDto.getCalle()) || Utilerias.existeInfo(emisorDto.getNumeroExterior()) || 
				Utilerias.existeInfo(emisorDto.getNumeroInterior()) ||	Utilerias.existeInfo(emisorDto.getColonia())){
			direccion = (Utilerias.existeInfo(emisorDto.getCalle()) ? 										emisorDto.getCalle() + " " 					: "");
			direccion = direccion + (Utilerias.existeInfo(emisorDto.getNumeroExterior()) ? 	"#" 		+ emisorDto.getNumeroExterior() + " " : "");
			direccion = direccion + (Utilerias.existeInfo(emisorDto.getNumeroInterior()) ? 	"-" 		+ emisorDto.getNumeroInterior() + " " : "");
			direccion = direccion + (Utilerias.existeInfo(emisorDto.getColonia()) ? 				"Col. " + emisorDto.getColonia() + " " 				: "");
		}
		if(Utilerias.existeInfo(emisorDto.getEstado()) || Utilerias.existeInfo(emisorDto.getMunicipio())){
			ubicacion = (Utilerias.existeInfo(emisorDto.getEstado()) ? 			emisorDto.getEstado() + ", " 		: "");
			ubicacion = ubicacion + (Utilerias.existeInfo(emisorDto.getMunicipio()) ? 	emisorDto.getMunicipio() + " " 	: "");
			ubicacion = ubicacion + (Utilerias.existeInfo(lugarExpedicion) ? 						lugarExpedicion + " " 	: "");
		}
		if(direccion != null || ubicacion != null ){
			ret = new Residencia();
			ret.setDireccion(direccion);
			ret.setUbicacion(ubicacion);
		}
		return ret;
	}
	private Logo toLogo(ArchivoRepDTO archivo){
		Logo ret = null;
		
		if(archivo != null){
			ret = new Logo();
			ret.setId(archivo.getCorrelacion());
			ret.setImagen(archivo.getContent());
		}
		
		return ret;
	}
}
