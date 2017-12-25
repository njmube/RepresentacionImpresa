package com.cubetech.representacion.impresa.interfaces.dto.catalogo;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=true, exclude={"valorMaximo"})
@ToString(callSuper=true, includeFieldNames=false)
public class TipoComprobanteDTO extends CatalogoDTO {
	
	private static final long serialVersionUID = 1291288905688838230L;
	private Map<String, Double> valorMaximo;
	public static final String VM = "ValorMaximo";
	
	public TipoComprobanteDTO(){
		
	}
	public TipoComprobanteDTO(TipoComprobanteSimpleDTO[] tipoCompronateSimple){
		//Hace falta validacion para si no encuentra el tipo de comprobante
		super(tipoCompronateSimple[0]);
		valorMaximo = new HashMap<>();
		int i;
		
		if(tipoCompronateSimple.length == 1){
			this.valorMaximo.put(VM, tipoCompronateSimple[0].getValorMaximoImporte());
		}else {
			for(i = 0 ; i < tipoCompronateSimple.length ; i++){
				this.valorMaximo.put(tipoCompronateSimple[i].getValorMaximoClasificacion(), tipoCompronateSimple[i].getValorMaximoImporte());
			}
		}
	}
	
}
