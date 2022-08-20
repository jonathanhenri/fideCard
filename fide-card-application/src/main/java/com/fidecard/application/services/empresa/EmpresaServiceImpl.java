package com.fidecard.application.services.empresa;

import com.fidecard.application.model.Empresa;
import com.fidecard.application.services.AbstractService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpresaServiceImpl extends AbstractService<Empresa, EmpresaRepository> implements EmpresaService {
	
	public EmpresaServiceImpl(EmpresaRepository repository) {
		super(repository);
	}
	
	
	@Override
	public List<Empresa> findAll() {
		return (List<Empresa>) getRepository().findAll();
	}
	
	@Override
	public boolean validarCreate(Empresa bean) {
		return false;
	}
	
}
