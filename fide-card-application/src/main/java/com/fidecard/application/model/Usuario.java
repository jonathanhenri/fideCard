package com.fidecard.application.model;

import com.fidecard.application.enuns.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Usuario {
	
	private String hashLogin;
	private String hashSenha;
	private TipoUsuario tipoUsuario;
	
}
