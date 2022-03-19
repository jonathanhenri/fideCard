package com.fidecard.application.seguranca.api;

import com.fidecard.application.services.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UsuarioService usuarioService;
    private final ApiJwtProvider tokenProvider;

    @Autowired
    public LoginService(ApiJwtProvider tokenProvider,
                        UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.tokenProvider = tokenProvider;
    }

//    /**
//     * Verifica usuario e senha e devolve um JWT válido.
//     */
//    public String login(UsuarioDto autenticacaoUsuarioDto) {
//        EncriptaDecriptaAES aes = new EncriptaDecriptaAES();
//        Usuario usuario = usuarioService.findByLogin(aes.encrypt(autenticacaoUsuarioDto.getHashLogin()));
//        if (usuario == null || !usuario.conferirSenha(autenticacaoUsuarioDto.getSenha())) {
//            throw new BadCredentialsException(messageBuilder.getMessage(SEGURANCA_USUARIO_NAO_ENCONTRADO));
//        }
//        return Optional.of(usuario)
//                .map(UsuarioDtoFactory::build)
//                .map(ApiUserAuthenticatedDto::new)
//                .map(tokenProvider::createToken)
//                .orElseThrow(() -> new BadCredentialsException(messageBuilder.getMessage(SEGURANCA_FALHA_JWT)));
//    }
//
//    public String refreshToken(UserAuthenticatedDto userAuthenticatedDto) {
//        return tokenProvider.createToken(userAuthenticatedDto);
//    }
}
