package com.fidecard.common.cartaoFidelidade;

import com.fidecard.common.AbstractDto;
import com.fidecard.common.cliente.ClienteDto;
import com.fidecard.common.empresa.EmpresaDto;
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
public class CartaoFidelidadeDto extends AbstractDto {
	
	@ApiModelProperty(name = "Empresa")
	private EmpresaDto empresa;
	
	@ApiModelProperty(name = "Cliente")
	private ClienteDto cliente;
	
	private Integer statusCartao;
	
	@ApiModelProperty(name = "Regra do Cartão")
	private List<RegraCartaoDto> listRegrasCartaoDto;
	
	@ApiModelProperty(name = "Layout Cartao")
	private LayoutCartaoDto layoutCartaoDto;
	
}
