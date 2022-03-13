package com.fidecard.application.enuns;

import lombok.Getter;

/**
 * Enum para diferenciar os tipos de usuarios existentes
 */
@Getter
public enum TipoUsuario {
	CLIENTE(0, "Cliente"),
	EMPRESA(1, "Empresa");
	
	private final Integer codigo;
	private final String descricao;
	
	TipoUsuario(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static TipoUsuario fromValue(Integer codigo) {
		if (codigo != null) {
			for (TipoUsuario status : values()) {
				if (status.getCodigo().equals(codigo)) {
					return status;
				}
			}
		}
		return null;
	}
}
