package com.fidecard.application.seguranca;

import lombok.Getter;

/**
 * Permissões de acesso à aplicação
 */
public enum Permissao {
	
	/**
	 * Permissão apenas aos endpoints relacionados ao usuario, é a permissão padrão para os usuários do
	 * aplicativo
	 */
	USUARIO("USUARIO"),
	
	/**
	 * Permissão para o sistema que se integra de forma administrativa a API
	 */
	INTEGRACAO_ADMINISTRATIVA("INTEGRACAO_ADMINISTRATIVA");
	
	@Getter
	private final String nome;
	
	private Permissao(String nome) {
		this.nome = nome;
	}
}
