package com.fidecard.application.utils.exceptions;

public class CreateException extends ServiceException {
	
	private static final long serialVersionUID = 570157162065009550L;
	
	public CreateException() {
		super(CreateException.class.getSimpleName());
	}
	
	public CreateException(String message) {
		super(message);
	}
	
	public CreateException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CreateException(Throwable cause) {
		super(cause.getMessage(), cause);
	}
	
}
