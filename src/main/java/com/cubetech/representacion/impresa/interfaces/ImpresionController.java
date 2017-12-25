package com.cubetech.representacion.impresa.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cubetech.representacion.impresa.application.ImpresionService;
import com.cubetech.representacion.impresa.interfaces.dto.ImpresionDTO;

@RestController
public class ImpresionController {

	private ImpresionService impresionService;
	
	@Autowired
	public ImpresionController(ImpresionService impresionService){
		this.impresionService = impresionService;
	}
	
	@RequestMapping(value="/Imprimir", method=RequestMethod.POST)
	public String representacionImpresa(@RequestHeader(value="cuenta") String cuenta, @RequestBody ImpresionDTO impresionDto){
		return this.impresionService.generaRepresentacionImpresa(cuenta, impresionDto);
	}
	
}
