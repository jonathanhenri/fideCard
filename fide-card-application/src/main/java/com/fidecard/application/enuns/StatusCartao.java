package com.fidecard.application.enuns;

import lombok.Getter;

/**
 * Status para saber como está a situacao do cartao
 */
@Getter
public enum StatusCartao {
	
	ABERTO(0, "Aberto"),
	FECHADO(1, "Fechado");
	
	private final Integer codigo;
	private final String descricao;
	
	StatusCartao(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static StatusCartao fromValue(Integer codigo) {
		if (codigo != null) {
			for (StatusCartao status : values()) {
				if (status.getCodigo().equals(codigo)) {
					return status;
				}
			}
		}
		return null;
	}
}
