package com.fidecard.common.cartaoFidelidade;

import com.fidecard.common.AbstractDto;
import io.swagger.annotations.ApiModel;
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
@ApiModel(description = "Regra do Cartão")
public class RegraCartaoDto extends AbstractDto {
	
	private Integer tipoRegraCartao;
	private Double valor;
	private String localDateTime;
	
}
