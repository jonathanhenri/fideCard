package com.fidecard.application.jms.cartaoFidelidade;

import static com.fidecard.application.jms.Filas.CLIENTE_CADASTRO;

import com.fidecard.application.conversoes.ClienteConvertUtil;
import com.fidecard.application.jms.PropriedadesJmsCadastro;
import com.fidecard.application.model.Cliente;
import com.fidecard.application.services.cartaoFidelidade.CartaoFidelidadeService;
import com.fidecard.application.utils.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service
public class CartaoFidelidadeListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CartaoFidelidadeListener.class);
	
	private final CartaoFidelidadeService cartaoFidelidadeService;
	
	@Autowired
	public CartaoFidelidadeListener(CartaoFidelidadeService cartaoFidelidadeService) {
		this.cartaoFidelidadeService = cartaoFidelidadeService;
	}
	
	@JmsListener(destination = CLIENTE_CADASTRO)
	public void onMessage(TextMessage message) {
		try {
			String jsonModel =
					message.getStringProperty(PropriedadesJmsCadastro.JSON_MODEL.name());
			
			Cliente cliente = ClienteConvertUtil.convertClienteDtoToCliente(jsonModel);
			
			cartaoFidelidadeService.validarCreate(cliente);
			cartaoFidelidadeService.create(cliente);
		} catch (JMSException | ServiceException e) {
			LOGGER.error("Falha ao processar mensagem da fila {}.", CLIENTE_CADASTRO, e);
		}
	}
	
}
