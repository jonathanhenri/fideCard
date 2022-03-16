package com.fidecard.common.cliente;

import com.fidecard.common.Dto;
import com.fidecard.common.endereco.EnderecoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Cliente")
public class ClienteDto implements Dto {
	
	@ApiModelProperty(notes = "Hash Cpf")
	private String hashCpf;
	
	@ApiModelProperty(notes = "Hash Senha")
	private String hashSenha;
	
	@ApiModelProperty(notes = "Nome")
	private String nome;
	
	@ApiModelProperty(notes = "Data de Nascimento")
	private Date dataNascimento;
	
	@ApiModelProperty(notes = "Endereço")
	private EnderecoDto enderecoDto;
	
	private Date dataUltimoAcesso;
	
}
