package com.fidecard.common.cliente;

import com.fidecard.common.Dto;
import com.fidecard.common.endereco.EnderecoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ExampleProperty;
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
@ApiModel(description = "Cliente")
public class ClienteDto implements Dto {
	
	private String hashCpf;
	private String hashSenha;
	private String nome;
	
	@ApiModelProperty(example = "1995-03-26T12:25:59-03:00")
	private String dataNascimento;
	
	@ApiModelProperty(notes = "Endereço")
	private EnderecoDto enderecoDto;
	
}
