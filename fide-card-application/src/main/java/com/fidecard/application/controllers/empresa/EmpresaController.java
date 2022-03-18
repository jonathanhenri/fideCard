package com.fidecard.application.controllers.empresa;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fidecard.application.jms.empresa.EmpresaGateway;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.common.empresa.EmpresaDto;
import com.fidecard.common.utils.RetornoSubmitDireto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Empresa")
@RestController
public class EmpresaController {
	
	private final EmpresaGateway empresaGateway;
	private static final String BASE_URL = "/empresa";
	public static final String URL_CADASTRAR = BASE_URL + "/iapi/cadastrar";
	public static final String URL_ATUALIZAR = BASE_URL + "/iapi/atualizar";
	public static final String URL_DELETAR = BASE_URL + "/iapi/deletar";
	
	@Autowired
	public EmpresaController(EmpresaGateway empresaGateway) {
		Assert.notNull(empresaGateway, "empresaGateway obrigatorio");
		this.empresaGateway = empresaGateway;
	}
	
	/**
	 * Importa um novo empresa
	 *
	 * @param empresaDto novo
	 */
	@PostMapping(value = URL_CADASTRAR, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Importar Cadastro de Empresa", notes = "Importa um cadastro de Empresa") //TODO FAZER SEGURANÇA
	public RetornoSubmitDireto cadastrarEmpresa(@RequestBody EmpresaDto empresaDto) {
		if (empresaDto == null ) {
			throw new ServiceException("Dados do empresaDto Vazio");
		}
		
		empresaGateway.cadastrar(empresaDto);
		return RetornoSubmitDireto.builder().mensagem("Cadastro de empresaDto enviado para processamento.")
				.httpStatus(HttpStatus.ACCEPTED.value()).build();
	}
	
	
	/**
	 * Atualiza empresa
	 *
	 * @param empresaDto atualizar
	 */
	@PostMapping(value = URL_ATUALIZAR, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Atualiza Cadastro Empresa", notes = "Atualiza um cadastro de Empresa") //TODO FAZER SEGURANÇA
	public RetornoSubmitDireto atualizarEmpresa(@RequestBody EmpresaDto empresaDto) {
		if (empresaDto == null ) {
			throw new ServiceException("Dados do Empresa Vazio");
		}
		
		empresaGateway.atualizar(empresaDto);
		return RetornoSubmitDireto.builder().mensagem("Atualização de empresa enviado para processamento.")
				.httpStatus(HttpStatus.ACCEPTED.value()).build();
	}
	
	/**
	 * Deletar empresa
	 *
	 * @param empresaDto deletar
	 */
	@PostMapping(value = URL_DELETAR, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Deletar Empresa", notes = "Deleta Empresa") //TODO FAZER SEGURANÇA
	public RetornoSubmitDireto deletarEmpresa(@RequestBody EmpresaDto empresaDto) {
		if (empresaDto == null ) {
			throw new ServiceException("Dados do empresaDto Vazio");
		}
		
		empresaGateway.deletar(empresaDto);
		return RetornoSubmitDireto.builder().mensagem("Deletar vinculo de empresaDto-empresa enviado para processamento.")
				.httpStatus(HttpStatus.ACCEPTED.value()).build();
	}
}
