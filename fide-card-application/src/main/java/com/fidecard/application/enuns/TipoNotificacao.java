package com.fidecard.application.enuns;

import lombok.Getter;

/**
 * Tipo de notificacao
 */
@Getter
public enum TipoNotificacao {
	
	ERRO(0, "Erro"),
	INFORMACAO(1, "Informação"),
	AVISO(2, "Aviso"),
	BENEFICIOS(3, "Beneficios");
	
	private final Integer codigo;
	private final String descricao;
	
	TipoNotificacao(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static TipoNotificacao fromValue(Integer codigo) {
		if (codigo != null) {
			for (TipoNotificacao status : values()) {
				if (status.getCodigo().equals(codigo)) {
					return status;
				}
			}
		}
		return null;
	}
}
