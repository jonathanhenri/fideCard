package com.fidecard.application.enuns;

import lombok.Getter;

/**
 * Enum para identificar cada tipo de regra dos cartões
 */
@Getter
public enum TipoRegraCartao {
	REGRA_VALOR(0, "Regra por Valor"),
	REGRA_DATA_VALIDADE(1, "Regra por Data de Validade");
	
	private final Integer codigo;
	private final String descricao;
	
	TipoRegraCartao(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static TipoRegraCartao fromValue(Integer codigo) {
		if (codigo != null) {
			for (TipoRegraCartao status : values()) {
				if (status.getCodigo().equals(codigo)) {
					return status;
				}
			}
		}
		return null;
	}
}
