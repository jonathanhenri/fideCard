package com.fidecard.application.services.usuario;

import com.fidecard.application.model.usuario.Usuario;
import com.fidecard.application.seguranca.Permissao;
import com.fidecard.application.services.AbstractService;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

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
	public Usuario create(Usuario usuario) {
		usuario.setPermissoes(Collections.singleton(Permissao.USUARIO));
		return super.create(usuario);
	}
	
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) getRepository().findAll();
	}
	
	@Override
	public Usuario findByHashLogin(byte[] hashLogin) {
		return usuarioRepository.findByHashLogin(hashLogin);
	}
	
}
