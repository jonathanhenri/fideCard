package com.fidecard.application.jms.cartaoFidelidade;

import static com.fidecard.application.jms.Filas.CARTAO_FIDELIDADE_CADASTRO;

import com.fidecard.application.conversoes.cartaoFidelidade.CartaoFidelidadeConvertUtil;
import com.fidecard.application.model.CartaoFidelidade;
import com.fidecard.application.services.cartaoFidelidade.CartaoFidelidadeService;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.common.cartaoFidelidade.CartaoFidelidadeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

@Service
public class CartaoFidelidadeListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CartaoFidelidadeListener.class);
	
	private final CartaoFidelidadeService cartaoFidelidadeService;
	private final CartaoFidelidadeConvertUtil cartaoFidelidadeConvertUtil;
	
	@Autowired
	public CartaoFidelidadeListener(CartaoFidelidadeService cartaoFidelidadeService,
									CartaoFidelidadeConvertUtil cartaoFidelidadeConvertUtil) {
		this.cartaoFidelidadeService = cartaoFidelidadeService;
		this.cartaoFidelidadeConvertUtil = cartaoFidelidadeConvertUtil;
	}
	
	@JmsListener(destination = CARTAO_FIDELIDADE_CADASTRO)
	public void onMessage(ObjectMessage message) {
		try {
			CartaoFidelidadeDto cartaoFidelidadeDto = (CartaoFidelidadeDto) message.getObject();
			
			CartaoFidelidade cartaoFidelidade = cartaoFidelidadeConvertUtil.convertCartaoDtoToCartao(cartaoFidelidadeDto);
			
			cartaoFidelidadeService.validarCreate(cartaoFidelidade);
			cartaoFidelidadeService.create(cartaoFidelidade);
		} catch (JMSException | ServiceException e) {
			LOGGER.error("Falha ao processar mensagem da fila {}.", CARTAO_FIDELIDADE_CADASTRO, e);
		}
	}
	
}
