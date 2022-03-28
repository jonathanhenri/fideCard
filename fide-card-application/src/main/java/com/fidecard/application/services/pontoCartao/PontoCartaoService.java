package com.fidecard.application.services.pontoCartao;

import com.fidecard.application.model.PontoCartao;
import com.fidecard.application.services.Service;

public interface PontoCartaoService extends Service<PontoCartao> {
	
	
	public boolean adicionaPontos(PontoCartao pontoCartao);
	
}
