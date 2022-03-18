package com.fidecard.application.services.empresa;

import com.fidecard.application.model.Empresa;
import com.fidecard.application.services.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl extends AbstractService<Empresa, EmpresaRepository> implements EmpresaService {
	
	public EmpresaServiceImpl(EmpresaRepository repository) {
		super(repository);
	}
	
	
	@Override
	public boolean validarCreate(Empresa bean) {
		return false;
	}
	
}
