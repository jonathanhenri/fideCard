package com.fidecard.application.conversoes;

import com.fidecard.application.model.Cliente;
import com.fidecard.application.utils.Utils;
import com.fidecard.common.cliente.ClienteDto;
import com.google.gson.Gson;

public class ClienteConvertUtil {
	
	
	public static Cliente convertClienteDtoToCliente(String jsonClienteDto) {
		ClienteDto clienteDto = new Gson().fromJson(jsonClienteDto, ClienteDto.class);
		
		Cliente cliente = Cliente.builder().hashCpf(clienteDto.getHashCpf()).hashSenha(clienteDto.getHashSenha())
				.nome(clienteDto.getNome()).dataNascimento(Utils.convertStringToData(clienteDto.getDataNascimento()))
				.endereco(EnderecoConvertUtil.convertEnderecoDtoToEndereco(clienteDto.getEnderecoDto())).build();
		
		return cliente;
	}
	
}
