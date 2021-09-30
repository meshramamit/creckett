package com.creckett.core.exception;

public class TechnicalException extends RuntimeException {
	public TechnicalException(String message){
		super(message);
	}
	
	public TechnicalException(Throwable cause){
		super(cause);
	}
	
	public TechnicalException(String message, Throwable cause){
		super(message,cause);
	}
}
