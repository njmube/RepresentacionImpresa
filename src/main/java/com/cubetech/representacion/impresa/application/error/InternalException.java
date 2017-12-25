package com.cubetech.representacion.impresa.application.error;

public class InternalException extends RuntimeException {

	private static final long serialVersionUID = 4811327557581254617L;
	private static final String GROUP = "04";  
	
	public InternalException(){
		super();
	}
	public InternalException(String message){
		super(message);
	}
	public InternalException(String message, Throwable cause ){
		super(message, cause);
	}
	
	public InternalException(Throwable cause ){
		super(cause);
	}
	
	public String getCode(){
		return GROUP;
	}
	
}
