package com.fidecard.application.conversoes;

import com.fidecard.application.model.usuario.Usuario;
import com.fidecard.common.usuario.UsuarioDto;
import com.google.gson.Gson;

public class UsuarioConvertUtil {
	
	
	public static Usuario convertUsuarioDtoToUsuario(String jsonUsuario) {
		UsuarioDto usuarioDto = new Gson().fromJson(jsonUsuario, UsuarioDto.class);
		
		Usuario usuario = Usuario.builder().hashLogin(usuarioDto.getLogin()).hashSenha(usuarioDto.getSenha()).build();
		
		return usuario;
	}
	
}
