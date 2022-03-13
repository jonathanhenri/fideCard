package com.fidecard.common.cliente;

import com.fidecard.common.Dto;
import com.fidecard.common.endereco.EnderecoDto;
import io.swagger.annotations.ApiModel;
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
@ApiModel(description = "Usuário")
public class ClienteDto implements Dto {
	
	private String hashCpf;
	private String hashSenha;
	private String nome;
	private Date dataNascimento;
	private Date dataUltimoAcesso;
	private EnderecoDto enderecoDto;
	
}
