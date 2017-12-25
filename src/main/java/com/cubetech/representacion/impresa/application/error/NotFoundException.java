package com.cubetech.representacion.impresa.application.error;

public class NotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2092041796473623636L;
	private static final String GROUP = "04";  
	
	public NotFoundException(){
		super();
	}
	public NotFoundException(String message){
		super(message);
	}
	public NotFoundException(String message, Throwable cause ){
		super(message, cause);
	}
	
	public NotFoundException(Throwable cause ){
		super(cause);
	}
	
	public String getCode(){
		return GROUP;
	}
}
