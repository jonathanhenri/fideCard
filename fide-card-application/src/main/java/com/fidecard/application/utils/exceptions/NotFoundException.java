package com.fidecard.application.utils.exceptions;

public class NotFoundException extends ServiceException {
	
	private static final long serialVersionUID = 1066762865616696939L;
	
	public NotFoundException() {
		super(NotFoundException.class.getSimpleName());
	}
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NotFoundException(Throwable cause) {
		super(cause.getMessage(), cause);
	}
	
}
