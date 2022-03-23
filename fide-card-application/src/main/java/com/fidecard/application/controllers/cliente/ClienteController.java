package com.fidecard.application.controllers.cliente;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fidecard.application.jms.cliente.ClienteGateway;
import com.fidecard.application.model.usuario.UsuarioDtoFactory;
import com.fidecard.application.services.usuario.UsuarioService;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.common.cliente.ClienteDto;
import com.fidecard.common.usuario.UsuarioDto;
import com.fidecard.common.utils.RetornoSubmitDireto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Cliente")
@RestController
public class ClienteController {
	
	private final ClienteGateway clienteGateway;
	private static final String BASE_URL = "/cliente";
	public static final String URL_CADASTRAR = BASE_URL + "/iapi/cadastrar";
	public static final String URL_ATUALIZAR = BASE_URL + "/iapi/atualizar";
	public static final String URL_DELETAR = BASE_URL + "/iapi/deletar";
	public static final String URL_CONSULTAR_ID = BASE_URL + "/iapi/consultar-id/{id}";
	
	@Autowired
	public ClienteController(ClienteGateway clienteGateway) {
		Assert.notNull(clienteGateway, "clienteGateway obrigatorio");
		this.clienteGateway = clienteGateway;
	}
	
	/**
	 * Importa um novo cliente
	 *
	 * @param cliente novo
	 */
	@PostMapping(value = URL_CADASTRAR, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Importar Cadastro Cliente", notes = "Importa um cadastro de cliente") //TODO FAZER SEGURANÇA
	public RetornoSubmitDireto cadastrarCliente(@RequestBody ClienteDto cliente) {
		if (cliente == null ) {
			throw new ServiceException("Dados do cliente Vazio");
		}
		
		clienteGateway.cadastrar(cliente);
		return RetornoSubmitDireto.builder().mensagem("Cadastro de cliente enviado para processamento.")
				.httpStatus(HttpStatus.ACCEPTED.value()).build();
	}
	
	
	/**
	 * Atualiza cliente
	 *
	 * @param cliente atualizar
	 */
	@PostMapping(value = URL_ATUALIZAR, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Atualiza Cadastro Cliente", notes = "Atualiza um cadastro de cliente") //TODO FAZER SEGURANÇA
	public RetornoSubmitDireto atualizarCliente(@RequestBody ClienteDto cliente) {
		if (cliente == null ) {
			throw new ServiceException("Dados do cliente Vazio");
		}
		
		clienteGateway.atualizar(cliente);
		return RetornoSubmitDireto.builder().mensagem("Atualização de cliente enviado para processamento.")
				.httpStatus(HttpStatus.ACCEPTED.value()).build();
	}
	
	/**
	 * Deletar cliente
	 *
	 * @param cliente deletar
	 */
	@PostMapping(value = URL_DELETAR, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Deleta Vinculo com o Cliente", notes = "Deleta Vinculo com o cliente-empresa") //TODO FAZER SEGURANÇA
	public RetornoSubmitDireto deletarCliente(@RequestBody ClienteDto cliente) {
		if (cliente == null ) {
			throw new ServiceException("Dados do cliente Vazio");
		}
		
		clienteGateway.deletar(cliente);
		return RetornoSubmitDireto.builder().mensagem("Deletar vinculo de cliente-empresa enviado para processamento.")
				.httpStatus(HttpStatus.ACCEPTED.value()).build();
	}
	

}
