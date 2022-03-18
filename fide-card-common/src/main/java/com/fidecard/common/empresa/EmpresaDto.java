package com.fidecard.common.empresa;

import com.fidecard.common.AbstractDto;
import com.fidecard.common.endereco.EnderecoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Empresa")
public class EmpresaDto extends AbstractDto {
	
	private String razaoSocial;
	private EnderecoDto endereco;
	private String responsavelNome;
	@ApiModelProperty(example = "999999")
	private String responsavelTelefone;
	@ApiModelProperty(example = "99999999999999")
	private String cnpj;
	
}
