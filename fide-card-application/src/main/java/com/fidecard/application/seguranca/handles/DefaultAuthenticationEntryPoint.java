package com.fidecard.application.seguranca.handles;

import br.com.oobj.i18n.MessageBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Se houver algum erro no spring security vai ser resolvido aqui.
 *
 */
@Component
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint, AccessDeniedMessageHandler,
        Serializable {

    @Getter
    private final transient MessageBuilder messageBuilder;

    @Autowired
    public DefaultAuthenticationEntryPoint(MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException {
        handleAccessDenied(response);
    }
}

