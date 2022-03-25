package com.fidecard.application.seguranca.handles;

import org.springframework.http.HttpStatus;

public class ErroDto {
	
	private Integer codigo;
	private String mensagem;
	
	public ErroDto() {
	}
	
	public ErroDto(Integer codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	public ErroDto(HttpStatus status, Exception ex) {
		this(status.value(), ex.getMessage());
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
