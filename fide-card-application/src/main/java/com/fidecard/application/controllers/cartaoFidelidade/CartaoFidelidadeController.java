package com.fidecard.application.controllers.cartaoFidelidade;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fidecard.application.controllers.AbstractController;
import com.fidecard.application.jms.cartaoFidelidade.CartaoFidelidadeGateway;
import com.fidecard.application.services.cartaoFidelidade.CartaoFidelidadeService;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.common.cartaoFidelidade.CartaoFidelidadeDto;
import com.fidecard.common.utils.RetornoSubmitDireto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Cartão de Fidelidade")
@RestController
public class CartaoFidelidadeController extends AbstractController {
	
	private final CartaoFidelidadeGateway cartaoFidelidadeGateway;
	
	private static final String BASE_URL_CARTAO = BASE_URL_API + "/cartao_fidelidade";
	public static final String URL_CADASTRAR = BASE_URL_CARTAO + "/cadastrar";
	public static final String URL_DELETAR = BASE_URL_CARTAO + "/deletar";
	private final CartaoFidelidadeService cartaoFidelidadeService;
	
	@Autowired
	public CartaoFidelidadeController(CartaoFidelidadeGateway cartaoFidelidadeGateway,
									  CartaoFidelidadeService cartaoFidelidadeService) {
		Assert.notNull(cartaoFidelidadeGateway, "cartaoFidelidadeGateway obrigatorio");
		Assert.notNull(cartaoFidelidadeService, "cartaoFidelidadeService obrigatorio");
		Assert.notNull(cartaoFidelidadeService, "pontoCartaoFidelidadeConvertUtil obrigatorio");
		
		this.cartaoFidelidadeService = cartaoFidelidadeService;
		this.cartaoFidelidadeGateway = cartaoFidelidadeGateway;
	}
	
	/**
	 * Importa um novo cartaoFidelidadeDto
	 *
	 * @param cartaoFidelidadeDto novo
	 */
	@PostMapping(value = URL_CADASTRAR, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Importar Cadastro de Cartão de Fidelidade", notes = "Importa um cadastro de Cartão de " +
			"Fidelidade")
	//TODO FAZER SEGURANÇA
	public RetornoSubmitDireto cadastrarCartaoFidelidade(@RequestBody CartaoFidelidadeDto cartaoFidelidadeDto) {
		if (cartaoFidelidadeDto == null) {
			throw new ServiceException("Dados do cartaoFidelidadeDto Vazio");
		}
		
		cartaoFidelidadeGateway.cadastrar(cartaoFidelidadeDto);
		return RetornoSubmitDireto.builder().mensagem("Cadastro de cartaoFidelidadeDto enviado para processamento.")
				.httpStatus(HttpStatus.ACCEPTED.value()).build();
	}
	
	
	/**
	 * Deletar cartaoFidelidadeDto
	 *
	 * @param cartaoFidelidadeDto deletar
	 */
	@PostMapping(value = URL_DELETAR, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Deleta Cartão de Fidelidade", notes = "Deleta Cartão de Fidelidade") //TODO FAZER SEGURANÇA
	public RetornoSubmitDireto deletarCartaoFidelidade(@RequestBody CartaoFidelidadeDto cartaoFidelidadeDto) {
		if (cartaoFidelidadeDto == null) {
			throw new ServiceException("Dados do cartaoFidelidadeDto Vazio");
		}
		
		cartaoFidelidadeGateway.deletar(cartaoFidelidadeDto);
		return RetornoSubmitDireto.builder()
				.mensagem("Deletar vinculo de cartaoFidelidadeDto-empresa enviado para processamento.")
				.httpStatus(HttpStatus.ACCEPTED.value()).build();
	}
	
}
