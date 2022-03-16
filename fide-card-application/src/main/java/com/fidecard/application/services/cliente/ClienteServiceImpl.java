package com.fidecard.application.services.cliente;

import com.fidecard.application.model.Cliente;
import com.fidecard.application.services.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl extends AbstractService<Cliente, ClienteRepository>
		implements ClienteService {
	
	public ClienteServiceImpl(ClienteRepository repository) {
		super(repository);
	}
	
	
	@Override
	public boolean validarCreate(Cliente bean) {
		return true;
	}
	
}
