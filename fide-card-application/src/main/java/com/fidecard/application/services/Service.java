package com.fidecard.application.services;

public interface Service<E> {
	
	E create(E bean);
	
	E update(E bean);
	
	void delete(E bean);
	
	void delete(Long id);
	
	E findById(Long id);
	
	boolean validarCreate(E bean);
	
}
