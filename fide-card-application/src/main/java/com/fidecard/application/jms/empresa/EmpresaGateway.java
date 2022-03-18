package com.fidecard.application.jms.empresa;

import static com.fidecard.application.jms.Filas.EMPRESA_ATUALIZAR;
import static com.fidecard.application.jms.Filas.EMPRESA_CADASTRO;
import static com.fidecard.application.jms.Filas.EMPRESA_DELETAR;

import com.fidecard.application.jms.CadastroGateway;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.common.empresa.EmpresaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class EmpresaGateway extends CadastroGateway {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaGateway.class);
	
	@Autowired
	public EmpresaGateway(JmsTemplate jmsTemplate) {
		super(jmsTemplate);
	}
	
	public void cadastrar(EmpresaDto empresaDto) {
		try {
			LOGGER.info("Enfileirando Cadastro Empresa {}", empresaDto.getRazaoSocial());
			enfileirar(EMPRESA_CADASTRO, toJson(empresaDto));
		} catch (IOException e) {
			throw new ServiceException("Falha ao importar Empresa", e);
		}
	}
	
	public void atualizar(EmpresaDto empresaDto) {
		try {
			LOGGER.info("Enfileirando Atualizar Empresa {}", empresaDto.getRazaoSocial());
			enfileirar(EMPRESA_ATUALIZAR, toJson(empresaDto));
		} catch (IOException e) {
			throw new ServiceException("Falha ao importar Empresa", e);
		}
	}
	
	public void deletar(EmpresaDto empresaDto) {
		try {
			LOGGER.info("Enfileirando Deletar Empresa {}", empresaDto.getRazaoSocial());
			enfileirar(EMPRESA_DELETAR, toJson(empresaDto));
		} catch (IOException e) {
			throw new ServiceException("Falha ao importar Empresa", e);
		}
	}

}
