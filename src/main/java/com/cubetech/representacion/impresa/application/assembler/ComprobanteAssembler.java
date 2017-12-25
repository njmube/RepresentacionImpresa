package com.cubetech.representacion.impresa.application.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cubetech.representacion.impresa.application.xml.comprobante.Comprobante;
import com.cubetech.representacion.impresa.application.xml.timbre.TimbreFiscalDigital;
import com.cubetech.representacion.impresa.domain.entidades.ComprobanteEntidad;
import com.cubetech.representacion.impresa.domain.valueobjects.Catalogo;
import com.cubetech.representacion.impresa.domain.valueobjects.Emisor;
import com.cubetech.representacion.impresa.domain.valueobjects.Impuesto;
import com.cubetech.representacion.impresa.domain.valueobjects.Moneda;
import com.cubetech.representacion.impresa.domain.valueobjects.Receptor;
import com.cubetech.representacion.impresa.domain.valueobjects.Residencia;
import com.cubetech.representacion.impresa.domain.valueobjects.Concepto;
import com.cubetech.representacion.impresa.interfaces.dto.DireccionDTO;
import com.cubetech.representacion.impresa.interfaces.dto.catalogo.CatalogoDTO;
import com.cubetech.representacion.impresa.interfaces.dto.catalogo.MonedaDTO;

public class ComprobanteAssembler {
	
	private ConceptoAssembler conceptoAssembler;
	private TotalesAssembler totalesAssembler;
	
	public ComprobanteAssembler(){
		this.conceptoAssembler = new ConceptoAssembler();
		this.totalesAssembler = new TotalesAssembler();
	}
	
	public ComprobanteEntidad toComprobanteEntidad(Comprobante comprobante, TimbreFiscalDigital timbre, 
																								Emisor emisor, String cadenaOriginalTFD, DireccionDTO receptorDir){
		ComprobanteEntidad ret = null;
		String tmp;
		
		if(comprobante != null){
			ret = new ComprobanteEntidad();
			
			if(Utilerias.existeInfo(comprobante.getSerie()) || Utilerias.existeInfo(comprobante.getFolio())){
				tmp = Utilerias.existeInfo(comprobante.getSerie())?  comprobante.getSerie() : "";
				tmp = tmp + (Utilerias.existeInfo(comprobante.getFolio())? (tmp.isEmpty()? comprobante.getFolio() : " - " + comprobante.getFolio()) : "");
				ret.setSerieFolio(tmp);
			}
			tmp = comprobante.getLugarExpedicion() + ", " + comprobante.getFecha().toString();
			ret.setLugarFecha(tmp);
			ret.setUuid(timbre.getUUID());
			ret.setSerieCertificado(comprobante.getNoCertificado());
			ret.setTipoComprobante(new Catalogo(comprobante.getTipoDeComprobante().toString()));
			ret.setMoneda(new Moneda(comprobante.getMoneda().toString()));
			emisor.setRfc(comprobante.getEmisor().getRfc());
			emisor.setNombre(comprobante.getEmisor().getNombre());
			emisor.setRegimen(new Catalogo(comprobante.getEmisor().getRegimenFiscal()));
			ret.setEmisor(emisor);
			ret.setReceptor(toReceptor(comprobante, receptorDir));
			ret.setConceptos(toConceptos(comprobante.getConceptos()));
			ret.setTotalLetra(ConvertidorLetras.convierteLetras(comprobante.getTotal().toBigInteger()));
			if(comprobante.getMetodoPago() != null && Utilerias.existeInfo(comprobante.getMetodoPago().toString()))
				ret.setMetodoPago(new Catalogo(comprobante.getMetodoPago().toString()));
			if(comprobante.getFormaPago() != null && Utilerias.existeInfo(comprobante.getFormaPago().toString()))
				ret.setFormaPago(new Catalogo(comprobante.getFormaPago().toString()));
			if(Utilerias.existeInfo(comprobante.getCondicionesDePago()))
				ret.setCondicionesPago(comprobante.getCondicionesDePago());
			ret.setTotales(this.totalesAssembler.toImportes(comprobante));
			ret.setSerieCertificadoSat(timbre.getNoCertificadoSAT());
			ret.setFechaTimbre(timbre.getFechaTimbrado().toXMLFormat());
			ret.setRfcProveedor(timbre.getRfcProvCertif());
			ret.setSello(comprobante.getSello());
			ret.setSelloSat(timbre.getSelloSAT());
			ret.setCadenaComplementoSat(cadenaOriginalTFD);
			
		}
		return ret;
	}
	
	private Receptor toReceptor(Comprobante comprobante, DireccionDTO receptorDir){
		Receptor ret = new Receptor();
		
		ret.setRfc(comprobante.getReceptor().getRfc());
		
		if(Utilerias.existeInfo(comprobante.getReceptor().getNombre())) 
				ret.setNombre(comprobante.getReceptor().getNombre());
		ret.setUso(new Catalogo(comprobante.getReceptor().getUsoCFDI().toString()));
		ret.setResidencia(toRecidencia(receptorDir));
		
		return ret;
	}
	public Residencia toRecidencia(DireccionDTO receptorDir){
		Residencia ret = null;
		
		String direccion = null;
		String ubicacion = null;
		
		if(Utilerias.existeInfo(receptorDir.getCalle()) || Utilerias.existeInfo(receptorDir.getNumeroExterior()) || 
				Utilerias.existeInfo(receptorDir.getNumeroInterior()) ||	Utilerias.existeInfo(receptorDir.getColonia())){
			direccion = (Utilerias.existeInfo(receptorDir.getCalle()) ? 										receptorDir.getCalle() + " " 					: "");
			direccion = direccion + (Utilerias.existeInfo(receptorDir.getNumeroExterior()) ? 	"#" 		+ receptorDir.getNumeroExterior() + " " : "");
			direccion = direccion + (Utilerias.existeInfo(receptorDir.getNumeroInterior()) ? 	"-" 		+ receptorDir.getNumeroInterior() + " " : "");
			direccion = direccion + (Utilerias.existeInfo(receptorDir.getColonia()) ? 				"Col. " + receptorDir.getColonia() + " " 				: "");
		}
		if(Utilerias.existeInfo(receptorDir.getEstado()) || Utilerias.existeInfo(receptorDir.getMunicipio()) || 
						Utilerias.existeInfo(receptorDir.getCodigoPostal())){
			ubicacion = (Utilerias.existeInfo(receptorDir.getEstado()) ? 				receptorDir.getEstado() + ", " 		: "");
			ubicacion = ubicacion + (Utilerias.existeInfo(receptorDir.getMunicipio()) ? 		receptorDir.getMunicipio() + " " 	: "");
			ubicacion = ubicacion + (Utilerias.existeInfo(receptorDir.getCodigoPostal()) ? 	receptorDir.getCodigoPostal() + " " 	: "");
		}
		if(direccion != null || ubicacion != null ){
			ret = new Residencia();
			ret.setDireccion(direccion);
			ret.setUbicacion(ubicacion);
		}
		
		return ret;
	}
	
	public List<Concepto> toConceptos(Comprobante.Conceptos conceptos){
		List<Concepto> ret = null;
		
		if(Utilerias.existeInfo( conceptos.getConcepto())){
			ret = new ArrayList<>();
			for(Comprobante.Conceptos.Concepto tmp : conceptos.getConcepto()){
				ret.add(this.conceptoAssembler.toConcepto(tmp));
			}
		}
		
		return ret;
	}

	public void asignaCatalogos(ComprobanteEntidad comprobante, Map<String, CatalogoDTO> catalogosDto) {
		
		String llave = "tipoComprobante" + comprobante.getTipoComprobante().getClaveSat();
		if(catalogosDto.containsKey(llave)){
			comprobante.getTipoComprobante().setDescripcion(catalogosDto.get(llave).getDescripcion());
		}
		llave = "moneda" + comprobante.getMoneda().getClaveSat();
		if(catalogosDto.containsKey(llave)){
			Moneda moneda;
			moneda = comprobante.getMoneda();
			MonedaDTO monedaDto = (MonedaDTO) catalogosDto.get(llave);
			moneda.setDescripcion(monedaDto.getDescripcion());
			moneda.setDecimales(monedaDto.getDecimales());
		}
		if(comprobante.getMetodoPago() != null){
			llave = "metodoPago" + comprobante.getMetodoPago().getClaveSat();
			if(catalogosDto.containsKey(llave)){
				comprobante.getMetodoPago().setDescripcion(catalogosDto.get(llave).getDescripcion());
			}
		}
		if(comprobante.getFormaPago() != null){
			llave = "formaPago" + comprobante.getFormaPago().getClaveSat();
			if(catalogosDto.containsKey(llave)){
				comprobante.getFormaPago().setDescripcion(catalogosDto.get(llave).getDescripcion());
			}
		}
		llave = "regimenFiscal" + comprobante.getEmisor().getRegimen().getClaveSat();
		if(catalogosDto.containsKey(llave)){
			comprobante.getEmisor().getRegimen().setDescripcion(catalogosDto.get(llave).getDescripcion());
		}
		llave = "uso" + comprobante.getReceptor().getUso().getClaveSat();
		if(catalogosDto.containsKey(llave)){
			comprobante.getReceptor().getUso().setDescripcion(catalogosDto.get(llave).getDescripcion());
		}
		
		if(Utilerias.existeInfo(comprobante.getTotales().getImpuestosTotales())){
			for(Impuesto tmp : comprobante.getTotales().getImpuestosTotales()){
				llave = "impuesto" + tmp.getImpuesto().getClaveSat();
				if(catalogosDto.containsKey(llave)){
					tmp.getImpuesto().setDescripcion(catalogosDto.get(llave).getDescripcion() + tmp.getImpuesto().getDescripcion());
				}
			}
		}
		
	}
}
