package com.cubetech.representacion.impresa.domain.valueobjects;



import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true, exclude={"decimales"})
public class Moneda extends Catalogo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2884870113107314758L;
	private int decimales;
	
	public Moneda(String claveSat){
		super(claveSat);
		decimales =0;
	}
	

}
