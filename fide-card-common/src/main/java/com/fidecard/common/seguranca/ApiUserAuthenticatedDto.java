package com.fidecard.common.seguranca;

import com.fidecard.common.usuario.UsuarioDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Classe utilizada para mapear as informações de autenticação de usuário em um objeto serializado.
 *
 * Esta classe é destinada a autenticação API - Usuário final
 *
 */
@NoArgsConstructor
public class ApiUserAuthenticatedDto extends UserAuthenticatedDto {

    @Getter
    private UsuarioDto usuario;

    public ApiUserAuthenticatedDto(UsuarioDto usuario) {
        super(usuario.getHashLogin(), usuario.getPermissoes());
        this.usuario = usuario;
    }

}
