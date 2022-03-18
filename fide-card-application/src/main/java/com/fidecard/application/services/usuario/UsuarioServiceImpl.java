package com.fidecard.application.services.usuario;

import com.fidecard.application.model.Usuario;
import com.fidecard.application.services.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends AbstractService<Usuario, UsuarioRepository> implements UsuarioService {
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super(repository);
	}
	
	
	@Override
	public boolean validarCreate(Usuario bean) {
		return false;
	}
	
}
