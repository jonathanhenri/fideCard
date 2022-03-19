package com.fidecard.application.seguranca.secretkey;

import com.fidecard.application.seguranca.UserAuthority;
import com.fidecard.common.seguranca.UserAuthenticatedDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import java.util.stream.Collectors;

/**
 * Abstract para a secret key provider.
 *
 */
public abstract class AbstractSecretKeyProvider {

    public Authentication getAuthentication(String secretKey) {
        UserAuthenticatedDto usuario = getUserDetailsByUsername(secretKey);
        if (usuario == null) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(usuario, "",
                usuario.getPermissoes().stream().map(UserAuthority::new).collect(Collectors.toList()));
    }

    protected abstract UserAuthenticatedDto getUserDetailsByUsername(String username);
}
