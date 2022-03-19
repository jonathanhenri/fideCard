package com.fidecard.common.seguranca;

import com.fidecard.common.Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Classe Abstrata que compartilha informações entre os 2 tipos de integrações possíveis:
 * 1 - API - Usuário final
 * 2 - IAPI - Entre sistemas.
 *
 */
@Getter
@NoArgsConstructor
public abstract class UserAuthenticatedDto implements Dto {
    private static final long serialVersionUID = -6273527307716199849L;
    /**
     * Pode ser Login ou SecretKey
     */
    protected String identificacao;
    protected List<String> permissoes;

    public UserAuthenticatedDto(String identificacao, List<String> permissoes) {
        this.identificacao = identificacao;
        this.permissoes = permissoes;
    }
}
