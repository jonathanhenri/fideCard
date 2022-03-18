package com.fidecard.application.jms.cliente;

import static com.fidecard.application.jms.Filas.CLIENTE_ATUALIZAR;
import static com.fidecard.application.jms.Filas.CLIENTE_CADASTRO;
import static com.fidecard.application.jms.Filas.CLIENTE_DELETAR;

import com.fidecard.application.jms.CadastroGateway;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.common.cliente.ClienteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class ClienteGateway extends CadastroGateway {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteGateway.class);
	
	@Autowired
	public ClienteGateway(JmsTemplate jmsTemplate) {
		super(jmsTemplate);
	}
	
	public void cadastrar(ClienteDto cliente) {
		try {
			LOGGER.info("Enfileirando Cadastro Cliente {}", cliente.getNome());
			enfileirar(CLIENTE_CADASTRO, toJson(cliente));
		} catch (IOException e) {
			throw new ServiceException("Falha ao importar Cliente", e);
		}
	}
	
	public void atualizar(ClienteDto cliente) {
		try {
			LOGGER.info("Enfileirando Atualizar Cliente {}", cliente.getNome());
			enfileirar(CLIENTE_ATUALIZAR, toJson(cliente));
		} catch (IOException e) {
			throw new ServiceException("Falha ao importar Cliente", e);
		}
	}
	
	public void deletar(ClienteDto cliente) {
		try {
			LOGGER.info("Enfileirando Deletar Cliente {}", cliente.getNome());
			enfileirar(CLIENTE_DELETAR, toJson(cliente));
		} catch (IOException e) {
			throw new ServiceException("Falha ao importar Cliente", e);
		}
	}

}
