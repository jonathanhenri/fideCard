package com.fidecard.application.conversoes;

import com.fidecard.application.model.Empresa;
import com.fidecard.application.model.Usuario;
import com.fidecard.common.empresa.EmpresaDto;
import com.fidecard.common.usuario.UsuarioDto;
import com.google.gson.Gson;

public class UsuarioConvertUtil {
	
	
	public static Usuario convertUsuarioDtoToUsuario(String jsonUsuario) {
		UsuarioDto usuarioDto = new Gson().fromJson(jsonUsuario, UsuarioDto.class);
		
		Usuario usuario = Usuario.builder().hashLogin(usuarioDto.getHashLogin()).hashSenha(usuarioDto.getHashSenha()).build();
		
		return usuario;
	}
	
}
