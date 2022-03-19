package com.fidecard.application.services.sessao;

import com.fidecard.common.seguranca.ApiUserAuthenticatedDto;

/**
 * Controla as sessões do usuário
 *
 */
public interface SessaoService {
   ApiUserAuthenticatedDto getUsuarioApiLogado();

   ApiUserAuthenticatedDto getUsuarioApiCache(String cpf);

    void limparCacheUsuarioLogado();

    void limparCacheUsuarioApi(String cpf);
}
