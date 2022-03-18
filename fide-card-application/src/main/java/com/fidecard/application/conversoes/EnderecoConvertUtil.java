package com.fidecard.application.conversoes;

import com.fidecard.application.model.Endereco;
import com.fidecard.common.endereco.EnderecoDto;
import com.google.gson.Gson;

public class EnderecoConvertUtil {
	
	public static Endereco convertEnderecoDtoToEndereco(String jsonEnderecoDto) {
		EnderecoDto enderecoDto = new Gson().fromJson(jsonEnderecoDto, EnderecoDto.class);
		return convertEnderecoDtoToEndereco(enderecoDto);
	}
	
	public static Endereco convertEnderecoDtoToEndereco(EnderecoDto enderecoDto) {
		Endereco endereco = Endereco.builder().logradouro(enderecoDto.getLogradouro()).numero(enderecoDto.getNumero())
				.complemento(enderecoDto.getComplemento()).bairro(enderecoDto.getBairro())
				.codigoMunicipio(enderecoDto.getCodigoMunicipio()).nomeMunicipio(enderecoDto.getNomeMunicipio())
				.uf(enderecoDto.getUf()).cep(enderecoDto.getCep()).codigoPais(enderecoDto.getCodigoPais())
				.pais(enderecoDto.getPais()).telefone(enderecoDto.getTelefone()).build();
		
		return endereco;
	}
	
}
