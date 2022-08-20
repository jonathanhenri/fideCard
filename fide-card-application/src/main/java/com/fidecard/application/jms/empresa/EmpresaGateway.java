package com.fidecard.application.jms.empresa;

import static com.fidecard.application.jms.Filas.EMPRESA_ATUALIZAR;
import static com.fidecard.application.jms.Filas.EMPRESA_CADASTRO;
import static com.fidecard.application.jms.Filas.EMPRESA_DELETAR;

import com.fidecard.application.jms.CadastroGateway;
import com.fidecard.common.empresa.EmpresaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmpresaGateway extends CadastroGateway {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaGateway.class);
	
	@Autowired
	public EmpresaGateway(JmsTemplate jmsTemplate) {
		super(jmsTemplate);
	}
	
	public void cadastrar(EmpresaDto empresaDto) {
		LOGGER.info("Enfileirando Cadastro Empresa {}", empresaDto.getRazaoSocial());
		enfileirar(EMPRESA_CADASTRO, empresaDto);
	}
	
	public void atualizar(EmpresaDto empresaDto) {
		LOGGER.info("Enfileirando Atualizar Empresa {}", empresaDto.getRazaoSocial());
		enfileirar(EMPRESA_ATUALIZAR, empresaDto);
	}
	
	public void deletar(EmpresaDto empresaDto) {
		LOGGER.info("Enfileirando Deletar Empresa {}", empresaDto.getRazaoSocial());
		enfileirar(EMPRESA_DELETAR, empresaDto);
	}
	
}
