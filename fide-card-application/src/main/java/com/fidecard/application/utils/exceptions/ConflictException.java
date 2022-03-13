package com.fidecard.application.utils.exceptions;


public class ConflictException extends GeneralException {

    private static final long serialVersionUID = 0L;

    public ConflictException() {
        super(ConflictException.class.getSimpleName());
    }

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflictException(Throwable cause) {
    	super(cause.getMessage(), cause);
    }

}
