package com.fidecard.application.utils.exceptions;

public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 923451761339088549L;
	
	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
