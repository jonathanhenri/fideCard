package com.fidecard.application.seguranca.mensagens;


import br.com.oobj.i18n.MensagemInternacionalizada;

/**
 * ENUM que centraliza as mensagens relacionadas a segurança.
 *
 */
public enum SegurancaMensagem implements MensagemInternacionalizada {

    SEGURANCA_ACESSO_NEGADO("canhoto.digital.seguranca.acesso.negado"),
    SEGURANCA_USUARIO_NAO_ENCONTRADO("canhoto.digital.seguranca.usuario.nao.encontrado"),
    SEGURANCA_FALHA_JWT("canhoto.digital.seguranca.falha.jwt");

    private final String identificadorMensagem;

    SegurancaMensagem(String identificadorMensagem) {
        this.identificadorMensagem = identificadorMensagem;
    }

    @Override
    public String getIdentificadorMensagem() {
        return identificadorMensagem;
    }
}
