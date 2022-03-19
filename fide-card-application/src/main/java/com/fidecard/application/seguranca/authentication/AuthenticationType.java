package com.fidecard.application.seguranca.authentication;

import com.fidecard.application.seguranca.Permissao;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Centralizador de configurações de autenticação
 * <p>
 * API - Usuário Motorista
 * IAPI - Sistema autenticado (Oobj Monitor)
 *
 */
@Getter
@AllArgsConstructor
public enum AuthenticationType {

    API(AuthenticationTypeConstant.API, "/api/**", "Authorization", "Bearer ",
            Permissao.USUARIO);

    private final String name;
    private final String baseUrl;
    private final String header;
    private final String prefixHeader;
    private final Permissao role;
}
