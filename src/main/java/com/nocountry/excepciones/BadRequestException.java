package com.nocountry.excepciones;

public class BadRequestException extends RuntimeException{
	
	public BadRequestException(String mensaje) {
		super(mensaje);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 440551754710109202L;

}
