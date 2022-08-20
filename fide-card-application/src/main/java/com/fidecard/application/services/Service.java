package com.fidecard.application.services;

import java.util.List;

public interface Service<E> {
	
	E create(E bean);
	
	E update(E bean);
	
	void delete(E bean);
	
	void delete(Long id);
	
	E findById(Long id);
	
	List<E> findAll();
	
	boolean validarCreate(E bean);
	
}
