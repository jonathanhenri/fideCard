package com.fidecard.application.utils.exceptions;

public class UpdateException extends ServiceException {
    
    private static final long serialVersionUID = -475598665049670816L;
    
    public UpdateException() {
        super(UpdateException.class.getSimpleName());
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
    	super(cause.getMessage(), cause);
    }

}
