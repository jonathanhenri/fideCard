package com.fidecard.application.jms;

import com.fidecard.common.Dto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import javax.jms.ObjectMessage;

@Service
public class CadastroGateway {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CadastroGateway.class);
	
	private final JmsTemplate jmsTemplate;
	
	@Autowired
	public CadastroGateway(JmsTemplate jmsTemplate) {
		Assert.notNull(jmsTemplate, "jmsTemplate obrigatorio");
		this.jmsTemplate = jmsTemplate;
	}
	
	protected void enfileirar(String nomeFila, Dto dto) {
		LOGGER.info("Enfileirando nova mensagem na fila {}", nomeFila);
		
		jmsTemplate.send(nomeFila, session -> {
			final ObjectMessage mensagem = session.createObjectMessage();
			mensagem.setObject(dto);

			return mensagem;
		});
	}

}
