package com.fidecard.application.controllers.usuario;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fidecard.application.jms.empresa.EmpresaGateway;
import com.fidecard.application.jms.usuario.UsuarioGateway;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.common.empresa.EmpresaDto;
import com.fidecard.common.usuario.UsuarioDto;
import com.fidecard.common.utils.RetornoSubmitDireto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Usuario")
@RestController
public class UsuarioController {
	
	private final UsuarioGateway usuarioGateway;
	private static final String BASE_URL = "/usuario";
	public static final String URL_CADASTRAR = BASE_URL + "/iapi/cadastrar";
	public static final String URL_ATUALIZAR = BASE_URL + "/iapi/atualizar";
	public static final String URL_DELETAR = BASE_URL + "/iapi/deletar";
	
	@Autowired
	public UsuarioController(UsuarioGateway usuarioGateway) {
		Assert.notNull(usuarioGateway, "usuarioGateway obrigatorio");
		this.usuarioGateway = usuarioGateway;
	}
	
	/**
	 * Importa um novo empresa
	 *
	 * @param usuarioDto novo
	 */
	@PostMapping(value = URL_CADASTRAR, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Importar Cadastro de Usuario", notes = "Importa um cadastro de Usuario") //TODO FAZER SEGURANÇA
	public RetornoSubmitDireto cadastrarUsuario(@RequestBody UsuarioDto usuarioDto) {
		if (usuarioDto == null ) {
			throw new ServiceException("Dados do UsuarioDto Vazio");
		}
		
		usuarioGateway.cadastrar(usuarioDto);
		return RetornoSubmitDireto.builder().mensagem("Cadastro de usuarioDto enviado para processamento.")
				.httpStatus(HttpStatus.ACCEPTED.value()).build();
	}
	
	
	/**
	 * Atualiza Usuario
	 *
	 * @param usuarioDto atualizar
	 */
	@PostMapping(value = URL_ATUALIZAR, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Atualiza Cadastro Usuario", notes = "Atualiza um cadastro de Usuario") //TODO FAZER SEGURANÇA
	public RetornoSubmitDireto atualizarUsuario(@RequestBody UsuarioDto usuarioDto) {
		if (usuarioDto == null ) {
			throw new ServiceException("Dados do Empresa Vazio");
		}
		
		usuarioGateway.atualizar(usuarioDto);
		return RetornoSubmitDireto.builder().mensagem("Atualização de Usuario enviado para processamento.")
				.httpStatus(HttpStatus.ACCEPTED.value()).build();
	}
	
	/**
	 * Deletar Usuario
	 *
	 * @param usuarioDto deletar
	 */
	@PostMapping(value = URL_DELETAR, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Deletar Usuario", notes = "Deleta Usuario") //TODO FAZER SEGURANÇA
	public RetornoSubmitDireto deletarUsuario(@RequestBody UsuarioDto usuarioDto) {
		if (usuarioDto == null ) {
			throw new ServiceException("Dados do usuarioDto Vazio");
		}
		
		usuarioGateway.deletar(usuarioDto);
		return RetornoSubmitDireto.builder().mensagem("Deletar vinculo de usuarioDto-empresa enviado para processamento.")
				.httpStatus(HttpStatus.ACCEPTED.value()).build();
	}
}
