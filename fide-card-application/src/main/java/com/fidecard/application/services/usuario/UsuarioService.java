package com.fidecard.application.services.usuario;

import com.fidecard.application.model.Usuario;
import com.fidecard.application.services.Service;

public interface UsuarioService extends Service<Usuario> {

	Usuario findByLogin(String login);
}
