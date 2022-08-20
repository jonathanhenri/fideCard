package com.fidecard.application.jms.cliente;

import static com.fidecard.application.jms.Filas.CLIENTE_ATUALIZAR;
import static com.fidecard.application.jms.Filas.CLIENTE_CADASTRO;
import static com.fidecard.application.jms.Filas.CLIENTE_DELETAR;

import com.fidecard.application.jms.CadastroGateway;
import com.fidecard.common.cliente.ClienteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClienteGateway extends CadastroGateway {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteGateway.class);
	
	@Autowired
	public ClienteGateway(JmsTemplate jmsTemplate) {
		super(jmsTemplate);
	}
	
	public void cadastrar(ClienteDto cliente) {
		LOGGER.info("Enfileirando Cadastro Cliente {}", cliente.getNome());
		enfileirar(CLIENTE_CADASTRO, cliente);
	}
	
	public void atualizar(ClienteDto cliente) {
		LOGGER.info("Enfileirando Atualizar Cliente {}", cliente.getNome());
		enfileirar(CLIENTE_ATUALIZAR, cliente);
	}
	
	public void deletar(ClienteDto cliente) {
		LOGGER.info("Enfileirando Deletar Cliente {}", cliente.getNome());
		enfileirar(CLIENTE_DELETAR, cliente);
	}
	
}
