package com.fidecard.application.seguranca;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

/**
 * Classe será usada pelo Spring Security para controlar os acessos.
 *
 */
public class UserAuthority implements GrantedAuthority {
    private static final String PREFIX = "ROLE_";
    private final Permissao permissao;

    public UserAuthority(Permissao permissao) {
        this.permissao = permissao;
    }

    public UserAuthority(String permissaoStr) {
        if (StringUtils.startsWith(permissaoStr, PREFIX)) {
            permissaoStr = StringUtils.substring(permissaoStr, PREFIX.length());
        }
        this.permissao = Permissao.valueOf(permissaoStr);
    }

    @Override
    public String getAuthority() {
        return PREFIX + this.permissao.toString();
    }
}
