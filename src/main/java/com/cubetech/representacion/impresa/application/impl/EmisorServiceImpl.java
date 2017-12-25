package com.cubetech.representacion.impresa.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubetech.representacion.impresa.application.EmisorService;
import com.cubetech.representacion.impresa.application.assembler.EmisorAssembler;
import com.cubetech.representacion.impresa.application.assembler.Utilerias;
import com.cubetech.representacion.impresa.domain.valueobjects.Emisor;
import com.cubetech.representacion.impresa.interfaces.dto.ArchivoRepDTO;
import com.cubetech.representacion.impresa.interfaces.dto.EmisorDTO;
import com.cubetech.representacion.impresa.interfaces.restclient.ArchivoRepository;
import com.cubetech.representacion.impresa.interfaces.restclient.EmisorRepository;

@Service
public class EmisorServiceImpl implements EmisorService {
	
	private EmisorRepository emisorRepository;
	private ArchivoRepository archivoRepository;
	private EmisorAssembler emisorAssembler;
	
	@Autowired
	public EmisorServiceImpl(EmisorRepository emisorRepository, ArchivoRepository archivoRepository){
		this.emisorRepository = emisorRepository;
		this.archivoRepository = archivoRepository;
		this.emisorAssembler = new EmisorAssembler();
	}
	
	@Override
	public Emisor consultaInfoEmisor(String cuenta, String emisor, String lugarExpedicion) {
		EmisorDTO emisorDto = this.emisorRepository.consultaEmisor(cuenta, emisor);
		ArchivoRepDTO logo = null;
		Emisor ret;
		if(Utilerias.existeInfo(emisorDto.getLogo())){
			logo = this.archivoRepository.findbyCuentaCorrelacion(cuenta, emisorDto.getLogo());
		}
		ret = this.emisorAssembler.toEmisor(emisorDto, logo, lugarExpedicion);
		
		return ret;
	}

}
