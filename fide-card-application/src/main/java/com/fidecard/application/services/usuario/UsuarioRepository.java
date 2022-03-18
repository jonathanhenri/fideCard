package com.fidecard.application.services.usuario;

import com.fidecard.application.model.Usuario;
import com.fidecard.application.services.repository.CustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CustomRepository<Usuario> {
}
