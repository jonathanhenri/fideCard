package com.fidecard.common.utils;


import com.fidecard.common.Dto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Retorno Submit")
public class RetornoSubmitDireto implements Dto {
	
	private String mensagem;
	private Integer httpStatus;
	private long timestamp;
	
	@Builder
	public RetornoSubmitDireto(String mensagem, Integer httpStatus) {
		this.mensagem = mensagem;
		this.httpStatus = httpStatus;
		timestamp = System.currentTimeMillis();
	}
	
}
