package com.fidecard.application.jms.empresa;

import static com.fidecard.application.jms.Filas.EMPRESA_CADASTRO;

import com.fidecard.application.conversoes.EmpresaConvertUtil;
import com.fidecard.application.model.Empresa;
import com.fidecard.application.services.empresa.EmpresaService;
import com.fidecard.application.utils.exceptions.ServiceException;
import com.fidecard.common.empresa.EmpresaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

@Service
public class EmpresaListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaListener.class);
	
	private final EmpresaService empresaService;
	
	@Autowired
	public EmpresaListener(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}
	
	@JmsListener(destination = EMPRESA_CADASTRO)
	public void onMessage(ObjectMessage message) {
		try {
			EmpresaDto empresaDto = (EmpresaDto) message.getObject();
			Empresa empresa = EmpresaConvertUtil.convertEmpresaDtoToEmpresa(empresaDto);
			
			empresaService.validarCreate(empresa);
			empresaService.create(empresa);
		} catch (JMSException | ServiceException e) {
			LOGGER.error("Falha ao processar mensagem da fila {}.", EMPRESA_CADASTRO, e);
		}
	}
	
}
