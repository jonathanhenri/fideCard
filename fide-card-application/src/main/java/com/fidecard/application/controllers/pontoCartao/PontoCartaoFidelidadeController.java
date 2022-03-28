package com.fidecard.application.controllers.pontoCartao;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fidecard.application.controllers.AbstractController;
import com.fidecard.application.conversoes.PontoCartaoFidelidadeConvertUtil;
import com.fidecard.application.services.pontoCartao.PontoCartaoService;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.common.pontoCartao.PontoCartaoFidelidadeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Pontos Cartão de Fidelidade")
@RestController
public class PontoCartaoFidelidadeController extends AbstractController {
	
	private static final String BASE_URL_PONTO = BASE_URL_API + "/ponto_cartao";
	public static final String URL_ADD_PONTOS = BASE_URL_PONTO + "/add-pontos";
	private final PontoCartaoFidelidadeConvertUtil pontoCartaoFidelidadeConvertUtil;
	private final PontoCartaoService pontoCartaoService;
	
	@Autowired
	public PontoCartaoFidelidadeController(PontoCartaoFidelidadeConvertUtil pontoCartaoFidelidadeConvertUtil,
										   PontoCartaoService pontoCartaoService) {
		this.pontoCartaoFidelidadeConvertUtil = pontoCartaoFidelidadeConvertUtil;
		this.pontoCartaoService = pontoCartaoService;
	}
	
	/**
	 * Adiciona pontos ao cartaoFidelidadeDto
	 *
	 * @param pontoCartaoFidelidadeDto pontuacao
	 */
	@PostMapping(value = URL_ADD_PONTOS, consumes = {APPLICATION_JSON_VALUE}, produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Adiciona Pontos Cartão de Fidelidade", notes = "Adiciona Pontos Cartão de Fidelidade")
	//TODO FAZER SEGURANÇA
	public boolean addPontosCartao(@RequestBody PontoCartaoFidelidadeDto pontoCartaoFidelidadeDto) {
		if (pontoCartaoFidelidadeDto == null) {
			throw new ServiceException("Ponto Cartão Vazio");
		}
		
		return pontoCartaoService.adicionaPontos(
				pontoCartaoFidelidadeConvertUtil.convertPontoDtoToPontoDto(pontoCartaoFidelidadeDto));
	}
	
}
