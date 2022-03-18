package com.fidecard.application.conversoes;

import com.fidecard.application.services.cliente.ClienteService;
import com.fidecard.application.services.empresa.EmpresaService;
import com.fidecard.application.services.layoutCartao.LayoutCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractConvertUtil {
	protected ClienteService clienteService;
	protected EmpresaService empresaService;
	protected LayoutCartaoService layoutCartaoService;
	
	@Autowired
	public AbstractConvertUtil(ClienteService clienteService, EmpresaService empresaService,
							   LayoutCartaoService layoutCartaoService) {
		this.clienteService = clienteService;
		this.empresaService = empresaService;
		this.layoutCartaoService = layoutCartaoService;
	}
	
}
