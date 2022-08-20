package com.fidecard.application.conversoes;

import com.fidecard.application.model.Cliente;
import com.fidecard.application.utils.DateUtils;
import com.fidecard.common.cliente.ClienteDto;

public class ClienteConvertUtil {
	
	
	public static Cliente convertClienteDtoToCliente(ClienteDto clienteDto) {
		
		Cliente cliente = Cliente.builder().hashCpf(clienteDto.getCpf()).nome(clienteDto.getNome())
				.dataNascimento(DateUtils.convertStringToData(clienteDto.getDataNascimento()))
				.endereco(EnderecoConvertUtil.convertEnderecoDtoToEndereco(clienteDto.getEnderecoDto())).build();
		
		return cliente;
	}
	
	public static ClienteDto convertClienteToDto(Cliente cliente) {
		return ClienteDto.builder().nome(cliente.getNome()).build();
	}
	
}
