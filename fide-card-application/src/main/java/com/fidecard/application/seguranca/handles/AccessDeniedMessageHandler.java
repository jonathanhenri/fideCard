package com.fidecard.application.seguranca.handles;

import static com.fidecard.application.seguranca.mensagens.SegurancaMensagem.SEGURANCA_ACESSO_NEGADO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fidecard.i18n.MessageBuilder;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interface que centraliza a manipulação da requisição para retornar 401 com a mensagem.
 *
 */
public interface AccessDeniedMessageHandler {
    MessageBuilder getMessageBuilder();

    default void handleAccessDenied(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ObjectMapper mapper = new ObjectMapper();
        String message = getMessageBuilder().getMessage(SEGURANCA_ACESSO_NEGADO);
        ErroDto erroDto = new ErroDto(HttpStatus.UNAUTHORIZED.value(), message);
        response.getWriter().write(mapper.writeValueAsString(erroDto));
    }

}
