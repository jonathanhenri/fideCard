package com.fidecard.application.seguranca.api;

import static com.fidecard.application.seguranca.mensagens.SegurancaMensagem.SEGURANCA_FALHA_JWT;
import static com.fidecard.application.seguranca.mensagens.SegurancaMensagem.SEGURANCA_USUARIO_NAO_ENCONTRADO;

import com.fidecard.i18n.MessageBuilder;
import com.fidecard.application.model.usuario.Usuario;
import com.fidecard.application.model.usuario.UsuarioDtoFactory;
import com.fidecard.application.services.usuario.UsuarioService;
import com.fidecard.application.utils.EncriptaDecriptaAES;
import com.fidecard.common.seguranca.ApiUserAuthenticatedDto;
import com.fidecard.common.seguranca.UserAuthenticatedDto;
import com.fidecard.common.usuario.AutenticacaoUsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LoginService {
    private final UsuarioService usuarioService;
    private final ApiJwtProvider tokenProvider;
    private final MessageBuilder messageBuilder;

    @Autowired
    public LoginService(ApiJwtProvider tokenProvider,
                        UsuarioService usuarioService,
                        MessageBuilder messageBuilder) {
        this.usuarioService = usuarioService;
        this.tokenProvider = tokenProvider;
        this.messageBuilder = messageBuilder;
    }

    /**
     * Verifica usuario e senha e devolve um JWT válido.
     */
    public String login(AutenticacaoUsuarioDto autenticacaoUsuarioDto) {
        EncriptaDecriptaAES aes = EncriptaDecriptaAES.getInstance();
        Usuario usuario = usuarioService.findByHashLogin(aes.encrypt(autenticacaoUsuarioDto.getLogin()));
        if (usuario == null || !usuario.conferirSenha(autenticacaoUsuarioDto.getSenha())) {
            throw new BadCredentialsException(messageBuilder.getMessage(SEGURANCA_USUARIO_NAO_ENCONTRADO));
        }
        return Optional.of(usuario)
                .map(UsuarioDtoFactory::build)
                .map(ApiUserAuthenticatedDto::new)
                .map(tokenProvider::createToken)
                .orElseThrow(() -> new BadCredentialsException(messageBuilder.getMessage(SEGURANCA_FALHA_JWT)));
    }

    public String refreshToken(UserAuthenticatedDto userAuthenticatedDto) {
        return tokenProvider.createToken(userAuthenticatedDto);
    }
}
