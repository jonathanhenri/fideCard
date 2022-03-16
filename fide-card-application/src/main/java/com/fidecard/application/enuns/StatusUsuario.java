package com.fidecard.application.enuns;

import lombok.Getter;

/**
 * Status para saber como está a situacao do USUARIO
 */
@Getter
public enum StatusUsuario {
	
	ATIVO(0, "Ativo"),
	DESATIVADO(1, "Desativado");
	
	private final Integer codigo;
	private final String descricao;
	
	StatusUsuario(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static StatusUsuario fromValue(Integer codigo) {
		if (codigo != null) {
			for (StatusUsuario status : values()) {
				if (status.getCodigo().equals(codigo)) {
					return status;
				}
			}
		}
		return null;
	}
}
