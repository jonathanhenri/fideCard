package com.fidecard.application.seguranca.iapi;


import br.com.oobj.canhoto.digital.config.security.secretkey.AbstractSecretKeyProvider;
import br.com.oobj.canhoto.digital.dto.seguranca.iapi.IApiUserAuthenticatedDto;
import br.com.oobj.canhoto.digital.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Classe de representação do Usuario API para o Spring Security
 * É usada pelo para retornar o usuário no contexto.
 *
 * Esta classe é destinada a autenticação IAPI - Integração entre sistemas
 *
 */
@Service
public class IApiSecretKeyProvider extends AbstractSecretKeyProvider {

    private final SessaoService sessaoService;

    @Autowired
    public IApiSecretKeyProvider(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @Override
    protected IApiUserAuthenticatedDto getUserDetailsByUsername(String secretKey) {
        return Optional.ofNullable(sessaoService.getUsuarioIApiCache(secretKey))
                .orElse(null);
    }
}
