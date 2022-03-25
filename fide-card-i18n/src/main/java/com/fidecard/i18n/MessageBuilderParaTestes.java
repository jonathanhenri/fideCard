package com.fidecard.i18n;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Message Builder criado para ser utilizado em testes unitarioas que apeans retorna a mensagem passada
 *
 * @author Ricardo Faria
 */
@Service
@Profile("test")
public class MessageBuilderParaTestes extends MessageBuilder {

    public MessageBuilderParaTestes() {
        super(null);
    }

    @Override
    public String getMessage(MensagemInternacionalizada mensagem, Object... params) {
        return mensagem.toString();
    }

}
