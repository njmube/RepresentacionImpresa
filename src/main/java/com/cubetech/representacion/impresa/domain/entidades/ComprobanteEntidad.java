package com.cubetech.representacion.impresa.domain.entidades;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.cubetech.representacion.impresa.domain.Entidad;
import com.cubetech.representacion.impresa.domain.valueobjects.Catalogo;
import com.cubetech.representacion.impresa.domain.valueobjects.Concepto;
import com.cubetech.representacion.impresa.domain.valueobjects.Emisor;
import com.cubetech.representacion.impresa.domain.valueobjects.Importes;
import com.cubetech.representacion.impresa.domain.valueobjects.Moneda;
import com.cubetech.representacion.impresa.domain.valueobjects.Receptor;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude={"emisor", "receptor", "reporte"})
@Entity
@Table(name="COMPROBANTE")
public class ComprobanteEntidad implements Entidad<ComprobanteEntidad> {
	
	@Id
	private String uuid;
	
	@Embedded
	private Emisor emisor;
	
	@Embedded
	private Receptor receptor;
	@ManyToOne(optional = false)
	private Reporte reporte;
	
	private static String URL_SAT_COMPROBANTE = "https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx";
	@Transient
	private String serieFolio;
	@Transient
	private String lugarFecha;
	@Transient
	private String serieCertificado;
	@Transient
	private Catalogo tipoComprobante;
	@Transient
	private Moneda moneda;
	@Transient
	private String totalLetra;
	@Transient
	private Catalogo metodoPago;
	@Transient
	private Catalogo formaPago;
	@Transient
	private String condicionesPago;
	@Transient
	private Importes totales;
	@Transient
	private String serieCertificadoSat;
	@Transient
	private String fechaTimbre;
	@Transient
	private String rfcProveedor;
	@Transient
	private String sello;
	@Transient
	private String selloSat;
	@Transient
	private String cadenaComplementoSat;
	@Transient
	private List<Concepto> conceptos;

	@Override
	public boolean sameIdentityAs(ComprobanteEntidad other) {
		// TODO Auto-generated method stub
		return this.equals(other);
	}
	
	public List<Map<String,String>> consultaClavesCatalogos(){
		List<Map<String,String>> ret = new ArrayList<>();
		Map<String, String> impuestoMap;
		ret.add(clavesComprobante());
		impuestoMap = this.totales.consultaClavesCatalogos();
		if(impuestoMap != null)
			ret.add(impuestoMap);
		return ret;
	}
	private Map<String,String> clavesComprobante(){
		Map<String,String> ret= new HashMap<>();
		
		ret.put("tipoComprobante", this.tipoComprobante.getClaveSat());
		ret.put("moneda", this.moneda.getClaveSat());
		if(this.metodoPago != null)
			ret.put("metodoPago", this.metodoPago.getClaveSat());
		if(this.formaPago != null)
			ret.put("formaPago", this.formaPago.getClaveSat());
		ret.put("regimenFiscal", this.getEmisor().getRegimen().getClaveSat());
		ret.put("uso", this.receptor.getUso().getClaveSat());
		
		return ret;
	}
	
	public String codigoQR(){
		String ret = "";
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		UriComponents uriComponents;
		
		switch(this.tipoComprobante.getClaveSat()){
			case "I":
			case "E":
			case "P":
				params.add("id", this.uuid);
				params.add("re", this.emisor.getRfc());
				params.add("rr", this.receptor.getRfc());
				params.add("tt", this.getTotales().getTotal().toString());
				params.add("fe", this.getSello().substring(this.getSello().length() - 8));
			break;
		}
		
		uriComponents = UriComponentsBuilder.fromHttpUrl(URL_SAT_COMPROBANTE).queryParams(params).build();
		ret = uriComponents.toUri().toString();
		return ret;
	}
}
