package com.fidecard.application.utils.exceptions;

public class DeleteException extends ServiceException {
    
    private static final long serialVersionUID = 642639122109389524L;
    
    public DeleteException() {
        super(DeleteException.class.getSimpleName());
    }

    public DeleteException(String message) {
        super(message);
    }
    
    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
    	super(cause.getMessage(), cause);
    }

}
