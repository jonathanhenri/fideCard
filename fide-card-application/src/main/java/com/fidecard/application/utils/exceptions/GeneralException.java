package com.fidecard.application.utils.exceptions;

public class GeneralException extends RuntimeException {
	
	private static final long serialVersionUID = -7702109997636247329L;
	
	public GeneralException() {
		super();
	}
	
	public GeneralException(String message) {
		super(message);
	}
	
	public GeneralException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public GeneralException(Throwable cause) {
		super(cause);
	}
	
}
