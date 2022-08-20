package com.fidecard.application.jms.usuario;

import static com.fidecard.application.jms.Filas.USUARIO_ATUALIZAR;
import static com.fidecard.application.jms.Filas.USUARIO_CADASTRO;
import static com.fidecard.application.jms.Filas.USUARIO_DELETAR;

import com.fidecard.application.jms.CadastroGateway;
import com.fidecard.common.usuario.UsuarioDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class UsuarioGateway extends CadastroGateway {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioGateway.class);
	
	@Autowired
	public UsuarioGateway(JmsTemplate jmsTemplate) {
		super(jmsTemplate);
	}
	
	public void cadastrar(UsuarioDto usuarioDto) {
		LOGGER.info("Enfileirando Cadastro Usuario {}", usuarioDto.getLogin());
		enfileirar(USUARIO_CADASTRO, usuarioDto);
	}
	
	public void atualizar(UsuarioDto usuarioDto) {
		LOGGER.info("Enfileirando Atualizar Usuario {}", usuarioDto.getLogin());
		enfileirar(USUARIO_ATUALIZAR, usuarioDto);
	}
	
	public void deletar(UsuarioDto usuarioDto) {
		LOGGER.info("Enfileirando Deletar Usuario {}", usuarioDto.getLogin());
		enfileirar(USUARIO_DELETAR, usuarioDto);
	}
	
}
