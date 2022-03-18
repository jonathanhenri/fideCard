package com.fidecard.application.services.cartaoFidelidade;

import com.fidecard.application.model.CartaoFidelidade;
import com.fidecard.application.services.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class CartaoFidelidadeServiceImpl extends AbstractService<CartaoFidelidade, CartaoFidelidadeRepository>
		implements CartaoFidelidadeService {
	
	public CartaoFidelidadeServiceImpl(CartaoFidelidadeRepository repository) {
		super(repository);
	}
	
	
	@Override
	public boolean validarCreate(CartaoFidelidade bean) {
		return false;
	}
	
}
