package com.fidecard.common.cartaoFidelidade;

import com.fidecard.common.Dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Cartao Fidelidade")
public class CartaoFidelidadeDto implements Dto  {
	
	@ApiModelProperty(name = "Empresa")
	private Long empresaId;
	
	@ApiModelProperty(name = "Cliente")
	private Long clienteId;
	
	@ApiModelProperty(name = "Layout Cartao")
	private Long layoutCartaoId;
	
	private Integer statusCartao;
	
	@ApiModelProperty(name = "Regra do Cartão")
	private List<RegraCartaoDto> listRegrasCartaoDto;
}
