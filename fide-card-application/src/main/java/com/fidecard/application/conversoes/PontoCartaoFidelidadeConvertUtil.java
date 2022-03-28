package com.fidecard.application.conversoes;

import com.fidecard.application.controllers.AbstractController;
import com.fidecard.application.model.PontoCartao;
import com.fidecard.application.services.cartaoFidelidade.CartaoFidelidadeService;
import com.fidecard.common.pontoCartao.PontoCartaoFidelidadeDto;
import org.springframework.stereotype.Component;

@Component
public class PontoCartaoFidelidadeConvertUtil extends AbstractController {
	
	private final CartaoFidelidadeService cartaoFidelidadeService;
	
	public PontoCartaoFidelidadeConvertUtil(CartaoFidelidadeService cartaoFidelidadeService) {
		this.cartaoFidelidadeService = cartaoFidelidadeService;
	}
	
	public PontoCartao convertPontoDtoToPontoDto(PontoCartaoFidelidadeDto pontoCartaoFidelidadeDto) {
		
		PontoCartao pontoCartao = PontoCartao.builder().valor(pontoCartaoFidelidadeDto.getValor())
				.dataPontuacao(pontoCartaoFidelidadeDto.getDataPontuacao())
				.identificadorUnico(pontoCartaoFidelidadeDto.getIdentificadorUnico())
				.cartaoFidelidade(cartaoFidelidadeService.findById(pontoCartaoFidelidadeDto.getIdCartaoFidelidade()))
				.build();
		
		return pontoCartao;
	}
	
}
