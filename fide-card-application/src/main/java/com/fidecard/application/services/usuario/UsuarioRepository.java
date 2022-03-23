package com.fidecard.application.services.usuario;

import com.fidecard.application.model.usuario.Usuario;
import com.fidecard.application.services.repository.CustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CustomRepository<Usuario> {
	
	Usuario findByHashLogin(byte[] hashLogin);
}
