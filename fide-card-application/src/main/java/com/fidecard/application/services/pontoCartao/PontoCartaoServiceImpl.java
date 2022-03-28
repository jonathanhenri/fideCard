package com.fidecard.application.services.pontoCartao;

import com.fidecard.application.model.PontoCartao;
import com.fidecard.application.services.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class PontoCartaoServiceImpl extends AbstractService<PontoCartao, PontoCartaoRepository>
		implements PontoCartaoService {
	
	public PontoCartaoServiceImpl(PontoCartaoRepository repository) {
		super(repository);
	}
	
	
	@Override
	public boolean validarCreate(PontoCartao bean) {
		return false;
	}
	
	@Override
	public boolean adicionaPontos(PontoCartao pontoCartao) {
		return false;
	}
	
}
