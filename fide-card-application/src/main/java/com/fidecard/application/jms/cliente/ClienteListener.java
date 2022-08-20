package com.fidecard.application.jms.cliente;

import static com.fidecard.application.jms.Filas.CLIENTE_CADASTRO;

import com.fidecard.application.conversoes.ClienteConvertUtil;
import com.fidecard.application.model.Cliente;
import com.fidecard.application.services.cliente.ClienteService;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.common.cliente.ClienteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

@Service
public class ClienteListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteListener.class);
	
	private final ClienteService clienteService;
	
	@Autowired
	public ClienteListener(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@JmsListener(destination = CLIENTE_CADASTRO)
	public void onMessage(ObjectMessage message) {
		try {
			ClienteDto clienteDto = (ClienteDto) message.getObject();
			Cliente cliente = ClienteConvertUtil.convertClienteDtoToCliente(clienteDto);
			
			clienteService.validarCreate(cliente);
			clienteService.create(cliente);
		} catch (JMSException | ServiceException e) {
			LOGGER.error("Falha ao processar mensagem da fila {}.", CLIENTE_CADASTRO, e);
		}
	}
	
}
