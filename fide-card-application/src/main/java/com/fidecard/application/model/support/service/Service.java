package com.fidecard.application.model.support.service;

public interface Service<E> {
	
	E create(E bean);
	
	E update(E bean);
	
	void delete(E bean);
	
	void delete(Long id);
	
	E findById(Long id);
	
}
