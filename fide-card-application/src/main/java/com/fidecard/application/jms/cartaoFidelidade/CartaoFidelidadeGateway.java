package com.fidecard.application.jms.cartaoFidelidade;

import static com.fidecard.application.jms.Filas.CARTAO_FIDELIDADE_CADASTRO;
import static com.fidecard.application.jms.Filas.CARTAO_FIDELIDADE_DELETAR;

import com.fidecard.application.jms.CadastroGateway;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.common.cartaoFidelidade.CartaoFidelidadeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class CartaoFidelidadeGateway extends CadastroGateway {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CartaoFidelidadeGateway.class);
	
	@Autowired
	public CartaoFidelidadeGateway(JmsTemplate jmsTemplate) {
		super(jmsTemplate);
	}
	
	public void cadastrar(CartaoFidelidadeDto cartaoFidelidadeDto) {
		try {
			LOGGER.info("Enfileirando Cadastro de Cartão de Fidelidade");
			enfileirar(CARTAO_FIDELIDADE_CADASTRO, toJson(cartaoFidelidadeDto));
		} catch (IOException e) {
			throw new ServiceException("Falha ao importar Cartão de Fidelidade", e);
		}
	}
	
	public void deletar(CartaoFidelidadeDto cartaoFidelidadeDto) {
		try {
			LOGGER.info("Enfileirando Deletar de Cartão de Fidelidade");
			enfileirar(CARTAO_FIDELIDADE_DELETAR, toJson(cartaoFidelidadeDto));
		} catch (IOException e) {
			throw new ServiceException("Falha ao deletar Cartão de Fidelidade", e);
		}
	}
	
}
