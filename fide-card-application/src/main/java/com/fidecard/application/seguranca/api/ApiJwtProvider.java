package com.fidecard.application.seguranca.api;

import com.fidecard.application.seguranca.jwt.AbstractJwtProvider;
import com.fidecard.application.services.sessao.SessaoService;
import com.fidecard.application.utils.DateUtils;
import com.fidecard.common.seguranca.ApiUserAuthenticatedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ApiJwtProvider extends AbstractJwtProvider {

    private final SessaoService sessaoService;

    @Autowired
    public ApiJwtProvider(DateUtils dateUtils, SessaoService sessaoService) {
        super(dateUtils);
        this.sessaoService = sessaoService;
    }

    @Override
    protected ApiUserAuthenticatedDto getUserDetailsByUsername(String login) {
        return Optional.ofNullable(this.sessaoService.getUsuarioApiCache(login))
                .orElseThrow(() -> new IllegalStateException("Usuário não existe"));
    }
}
