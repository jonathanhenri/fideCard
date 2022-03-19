package com.fidecard.application.seguranca.secretkey;

import br.com.oobj.canhoto.digital.config.security.authentication.AuthenticationType;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Abstrai configuração do Secret Key para o Spring Security.
 * Secret Key é uma chave única de autorização que identifica o usuário no sistema.
 * Esse tipo de autenticação foi criada para ser entre sistemas.
 *
 */
public class SecretKeyConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    /**
     * Deve ser enviado no header da requisição com a chave de autenticação do sistema.
     */
    public static final String X_INTEGRATION_AUTH_TOKEN_HEADER = AuthenticationType.IAPI.getHeader();

    private final AbstractSecretKeyProvider secretKeyProvider;

    public SecretKeyConfigurer(AbstractSecretKeyProvider secretKeyProvider) {
        this.secretKeyProvider = secretKeyProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new SecretKeyFilter(secretKeyProvider), UsernamePasswordAuthenticationFilter.class);
    }

}