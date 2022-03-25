package br.com.oobj.message;

import com.fidecard.i18n.MensagemInternacionalizada;

public enum I18nEnumTest implements MensagemInternacionalizada {
    MENSAGEM_PADRAO("teste.mensagem.padrao"),
    MENSAGEM_INEXISTENTE("teste.mensagem.inexistente");

    private final String identificadorMensagem;

    I18nEnumTest(String identificadorMensagem) {
        this.identificadorMensagem = identificadorMensagem;
    }

    @Override
    public String getIdentificadorMensagem() {
        return identificadorMensagem;
    }
}
