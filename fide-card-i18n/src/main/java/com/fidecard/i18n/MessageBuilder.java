package com.fidecard.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * Servico construtor de mensagens internacionalizada
 *
 * @author Ricardo Faria
 */
@Service
public class MessageBuilder {

    private final MessageSource messageSource;

    @Autowired
    public MessageBuilder(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    /**
     * Obtem uma mensagem localizada para a mensagem recebida complementando os wildcards identificados por {?} com os parametros
     * na ordem recebida
     *
     * @param mensagem instancia de {@link MensagemInternacionalizada} que aponte para uma mensagem no provedor do recurso
     * @param params   array de parametetros recebidos na ordem em que serao substituidos
     * @return {@link String} com a mensagem localizada
     */
    public String getMessage(MensagemInternacionalizada mensagem, Object... params) {
        return messageSource.getMessage(mensagem.getIdentificadorMensagem(), params, LocaleContextHolder.getLocale());
    }

}	
