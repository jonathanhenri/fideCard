package com.fidecard.common.usuario;

import com.fidecard.common.Dto;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto para realizar autenticação
 *
 */
@Data
@NoArgsConstructor
@ApiModel(description = "Informações para autenticação do Usuario")
public class AutenticacaoUsuarioDto implements Dto {
    private String login;
    private String senha;

    @Builder
    public AutenticacaoUsuarioDto(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
    
}
