package com.fidecard.application.controllers.usuario;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fidecard.application.jms.usuario.UsuarioGateway;
import com.fidecard.application.model.usuario.UsuarioDtoFactory;
import com.fidecard.application.seguranca.authentication.AuthenticationTypeConstant;
import com.fidecard.application.services.usuario.UsuarioService;
import com.fidecard.application.utils.EncriptaDecriptaAES;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.common.usuario.UsuarioDto;
import com.fidecard.common.utils.RetornoSubmitDireto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@Api(tags = "Usuario")
@RestController
public class UsuarioController {
	
	private final UsuarioGateway usuarioGateway;
	private final UsuarioService usuarioService;
	private static final String BASE_URL = "/usuario";
	public static final String URL_CADASTRAR = BASE_URL + "/cadastrar";
	public static final String URL_ATUALIZAR = BASE_URL + "/api/atualizar";
	public static final String URL_DELETAR = BASE_URL + "/api/deletar";
	
	//TODO REMOVER ISSO DEPOIS
	public static final String URL_CONSULTAR_ID = BASE_URL + "/api/consultar-id/{id}";
	public static final String URL_CONSULTAR_LOGIN = BASE_URL + "/api/consultar-login/{login}";
	
	@Autowired
	public UsuarioController(UsuarioGateway usuarioGateway, UsuarioService usuarioService) {
		Assert.notNull(usuarioGateway, "usuarioGateway obrigatorio");
		Assert.notNull(usuarioGateway, "usuarioService obrigatorio");
		this.usuarioGateway = usuarioGateway;
		this.usuarioService = usuarioService;
	}
	
	/**
	 * Importa um novo empresa
	 *
	 * @param usuarioDto novo
	 */
	@PostMapping(value = URL_CADASTRAR, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Importar Cadastro de Usuario", notes = "Importa um cadastro de Usuario")
	//TODO FAZER SEGURANÇA
	public RetornoSubmitDireto cadastrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
		if (usuarioDto == null) {
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
	@ApiOperation(value = "Atualiza Cadastro Usuario", notes = "Atualiza um cadastro de Usuario", authorizations = {
			@Authorization(value = AuthenticationTypeConstant.API)})
	public RetornoSubmitDireto atualizarUsuario(@RequestBody UsuarioDto usuarioDto) {
		if (usuarioDto == null) {
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
	@ApiOperation(value = "Deletar Usuario", notes = "Deleta Usuario", authorizations = {
			@Authorization(value = AuthenticationTypeConstant.API)})
	public RetornoSubmitDireto deletarUsuario(@RequestBody UsuarioDto usuarioDto) {
		if (usuarioDto == null) {
			throw new ServiceException("Dados do usuarioDto Vazio");
		}
		
		usuarioGateway.deletar(usuarioDto);
		return RetornoSubmitDireto.builder()
				.mensagem("Deletar vinculo de usuarioDto-empresa enviado para processamento.")
				.httpStatus(HttpStatus.ACCEPTED.value()).build();
	}
	
	@GetMapping(value = URL_CONSULTAR_ID, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retorna dados de um usuario por id", authorizations = {
			@Authorization(value = AuthenticationTypeConstant.API)})
	public UsuarioDto getUsuarioById(@ApiParam("id") Long id) {
		return UsuarioDtoFactory.buildDecrypt(usuarioService.findById(id));
	}
	
	@GetMapping(value = URL_CONSULTAR_LOGIN, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retorna dados de um usuario por id", authorizations = {
			@Authorization(value = AuthenticationTypeConstant.API)})
	public UsuarioDto getUsuarioByLogin(@ApiParam("login") String login) {
		EncriptaDecriptaAES aes = EncriptaDecriptaAES.getInstance();
		
		return UsuarioDtoFactory.buildDecrypt(usuarioService.findByHashLogin(aes.encrypt(login)));
	}
	
}
