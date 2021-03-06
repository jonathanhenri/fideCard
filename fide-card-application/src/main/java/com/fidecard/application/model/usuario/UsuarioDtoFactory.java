package com.fidecard.application.model.usuario;

import com.fidecard.application.seguranca.Permissao;
import com.fidecard.common.usuario.UsuarioDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.stream.Collectors;

/**
 * Factory para converter entidade Usuario em Dto's
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioDtoFactory {

    public static UsuarioDto build(Usuario usuario) {
        return UsuarioDto.builder()
                .login(usuario.getLoginDescriptografado())
                .id(usuario.getId())
                .permissoes(usuario.getPermissoes().stream().map(Permissao::name).collect(Collectors.toList()))
                .build();
    }
    
    public static UsuarioDto buildDecrypt(Usuario usuario) {
        return UsuarioDto.builder()
                .login(usuario.getLoginDescriptografado())
                .id(usuario.getId())
                .permissoes(usuario.getPermissoes().stream().map(Permissao::name).collect(Collectors.toList()))
                .build();
        
    }
}
