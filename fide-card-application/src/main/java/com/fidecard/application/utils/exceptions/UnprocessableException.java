package com.fidecard.application.utils.exceptions;


public class UnprocessableException extends GeneralException {

    private static final long serialVersionUID = 0L;

    public UnprocessableException() {
        super(UnprocessableException.class.getSimpleName());
    }

    public UnprocessableException(String message) {
        super(message);
    }

    public UnprocessableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnprocessableException(Throwable cause) {
    	super(cause.getMessage(), cause);
    }

}
