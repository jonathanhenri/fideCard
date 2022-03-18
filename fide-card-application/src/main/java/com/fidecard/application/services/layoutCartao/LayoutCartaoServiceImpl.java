package com.fidecard.application.services.layoutCartao;

import com.fidecard.application.model.LayoutCartao;
import com.fidecard.application.services.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class LayoutCartaoServiceImpl extends AbstractService<LayoutCartao, LayoutCartaoRepository>
		implements LayoutCartaoService {
	
	public LayoutCartaoServiceImpl(LayoutCartaoRepository repository) {
		super(repository);
	}
	
	
	@Override
	public boolean validarCreate(LayoutCartao bean) {
		return false;
	}
	
}
