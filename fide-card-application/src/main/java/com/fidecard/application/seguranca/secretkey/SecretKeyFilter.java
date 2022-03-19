package com.fidecard.application.seguranca.secretkey;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Filtro Secret Key, este filtro usa uma chave única enviada no cabeçario para encontrar o usuário
 * com autorização para utilizar.
 * Este filtro deve ser usado para autenticação entre sistemas.
 *
 */
public class SecretKeyFilter extends GenericFilterBean {

    private final AbstractSecretKeyProvider secretKeyProvider;

    public SecretKeyFilter(AbstractSecretKeyProvider secretKeyProvider) {
        this.secretKeyProvider = secretKeyProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String secretKey = resolveToken(httpServletRequest);
            Optional.ofNullable(this.secretKeyProvider.getAuthentication(secretKey))
                    .ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ExpiredJwtException eje) {
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String secretKey = request.getHeader(SecretKeyConfigurer.X_INTEGRATION_AUTH_TOKEN_HEADER);
        return StringUtils.hasText(secretKey) ? secretKey : EMPTY;
    }
}
