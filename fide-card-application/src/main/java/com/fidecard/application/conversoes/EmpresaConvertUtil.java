package com.fidecard.application.conversoes;

import com.fidecard.application.model.Empresa;
import com.fidecard.common.empresa.EmpresaDto;
import com.google.gson.Gson;

public class EmpresaConvertUtil {
	
	
	public static Empresa convertEmpresaDtoToEmpresa(String jsonEmpresaDto) {
		EmpresaDto empresaDto = new Gson().fromJson(jsonEmpresaDto, EmpresaDto.class);
		
		Empresa empresa = Empresa.builder().razaoSocial(empresaDto.getRazaoSocial())
				.responsavelNome(empresaDto.getResponsavelNome())
				.responsavelTelefone(empresaDto.getResponsavelTelefone()).cnpj(empresaDto.getCnpj())
				.endereco(EnderecoConvertUtil.convertEnderecoDtoToEndereco(empresaDto.getEndereco())).build();
		
		return empresa;
	}
	
}
