package com.fidecard.application.jms.usuario;

import static com.fidecard.application.jms.Filas.EMPRESA_CADASTRO;
import static com.fidecard.application.jms.Filas.USUARIO_CADASTRO;

import com.fidecard.application.conversoes.UsuarioConvertUtil;
import com.fidecard.application.enuns.StatusUsuario;
import com.fidecard.application.jms.PropriedadesJmsCadastro;
import com.fidecard.application.model.usuario.Usuario;
import com.fidecard.application.services.usuario.UsuarioService;
import com.fidecard.application.utils.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service
public class UsuarioListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioListener.class);
	
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioListener(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@JmsListener(destination = USUARIO_CADASTRO)
	public void onMessage(TextMessage message) {
		try {
			String jsonModel =
					message.getStringProperty(PropriedadesJmsCadastro.JSON_MODEL.name());
			
			Usuario usuario = UsuarioConvertUtil.convertUsuarioDtoToUsuario(jsonModel);
			
			usuario.setStatusUsuario(StatusUsuario.ATIVO);
			
			usuarioService.validarCreate(usuario);
			usuarioService.create(usuario);
		} catch (JMSException | ServiceException e) {
			LOGGER.error("Falha ao processar mensagem da fila {}.", EMPRESA_CADASTRO, e);
		}
	}
	
}
