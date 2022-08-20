package com.fidecard.application.services.cliente;

import com.fidecard.application.model.Cliente;
import com.fidecard.application.services.AbstractService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServiceImpl extends AbstractService<Cliente, ClienteRepository>
		implements ClienteService {
	
	public ClienteServiceImpl(ClienteRepository repository) {
		super(repository);
	}
	
	
	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) getRepository().findAll();
	}
	
	@Override
	public boolean validarCreate(Cliente bean) {
		return true;
	}
	
}
