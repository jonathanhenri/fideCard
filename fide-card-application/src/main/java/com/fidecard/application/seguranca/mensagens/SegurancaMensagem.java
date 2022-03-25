package com.fidecard.application.seguranca.mensagens;


import com.fidecard.i18n.MensagemInternacionalizada;

/**
 * ENUM que centraliza as mensagens relacionadas a segurança.
 *
 */
public enum SegurancaMensagem implements MensagemInternacionalizada {

    SEGURANCA_ACESSO_NEGADO("seguranca.acesso.negado"),
    SEGURANCA_USUARIO_NAO_ENCONTRADO("seguranca.usuario.nao.encontrado"),
    SEGURANCA_FALHA_JWT("seguranca.falha.jwt");

    private final String identificadorMensagem;

    SegurancaMensagem(String identificadorMensagem) {
        this.identificadorMensagem = identificadorMensagem;
    }

    @Override
    public String getIdentificadorMensagem() {
        return identificadorMensagem;
    }
}
