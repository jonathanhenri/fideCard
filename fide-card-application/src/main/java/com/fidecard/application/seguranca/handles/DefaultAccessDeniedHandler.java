package com.fidecard.application.seguranca.handles;

import com.fidecard.i18n.MessageBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Requisições negadas pelo hasRole são tratadas aqui
 *
 */
@Component
public class DefaultAccessDeniedHandler implements AccessDeniedHandler, AccessDeniedMessageHandler {

    @Getter
    private final MessageBuilder messageBuilder;

    @Autowired
    public DefaultAccessDeniedHandler(MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException {
        handleAccessDenied(response);
    }

}
