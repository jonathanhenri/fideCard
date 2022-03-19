package com.fidecard.application.services.usuario;

import com.fidecard.application.model.Usuario;
import com.fidecard.application.services.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends AbstractService<Usuario, UsuarioRepository> implements UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super(repository);
		this.usuarioRepository = repository;
	}
	
	@Override
	public boolean validarCreate(Usuario bean) {
		return false;
	}
	
	@Override
	public Usuario findByLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}
	
}
