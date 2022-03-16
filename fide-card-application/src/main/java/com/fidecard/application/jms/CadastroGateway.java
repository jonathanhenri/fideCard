package com.fidecard.application.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import javax.jms.TextMessage;
import java.io.Serializable;

@Service
public class CadastroGateway {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CadastroGateway.class);
	
	private final JmsTemplate jmsTemplate;
	
	@Autowired
	public CadastroGateway(JmsTemplate jmsTemplate) {
		Assert.notNull(jmsTemplate, "jmsTemplate obrigatorio");
		this.jmsTemplate = jmsTemplate;
	}
	
	protected void enfileirar(String nomeFila, String jsonModel) {
		LOGGER.info("Enfileirando nova mensagem na fila {}", nomeFila);
		
		jmsTemplate.send(nomeFila, session -> {
			final TextMessage mensagem = session.createTextMessage();
			mensagem.setStringProperty(PropriedadesJmsCadastro.JSON_MODEL.name(), jsonModel);

			return mensagem;
		});
	}
	
	protected <O extends Serializable> String toJson(O object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}

}
