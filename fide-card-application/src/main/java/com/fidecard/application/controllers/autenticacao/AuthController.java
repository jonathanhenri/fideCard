package com.fidecard.application.controllers.autenticacao;

import com.fidecard.application.seguranca.api.LoginService;
import com.fidecard.application.seguranca.authentication.AuthenticationType;
import com.fidecard.application.seguranca.authentication.AuthenticationTypeConstant;
import com.fidecard.application.services.sessao.SessaoService;
import com.fidecard.common.seguranca.ApiUserAuthenticatedDto;
import com.fidecard.common.seguranca.RetornoTokenDto;
import com.fidecard.common.usuario.AutenticacaoUsuarioDto;
import com.fidecard.common.usuario.UsuarioDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável manipular autenticação do usuário
 *
 */
@Api(tags = "Autenticação")
@RestController
@RequestMapping
public class AuthController {
    public static final String LOGIN = "/api/login";
    public static final String X_AUTH_TOKEN_HEADER = "X-Auth-Token";

    private final LoginService loginService;
    private final SessaoService sessaoService;

    @Autowired
    public AuthController(LoginService loginService,
                          SessaoService sessaoService) {
        this.loginService = loginService;
        this.sessaoService = sessaoService;
    }
    
    @PostMapping(LOGIN)
    @ApiOperation(value = "Autenticação", notes = "Autentica usuário, rota não autenticada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário autenticado com sucesso."),
            @ApiResponse(code = 401, message = "Não foi possível validar as informações de login.")
    })
    public ResponseEntity<RetornoTokenDto> autenticar(@RequestBody AutenticacaoUsuarioDto autenticacaoUsuarioDto) {
        String jwt = loginService.login(autenticacaoUsuarioDto);
        return ResponseEntity
                .ok()
                .header(X_AUTH_TOKEN_HEADER, jwt)
                .body(new RetornoTokenDto(AuthenticationType.API.getPrefixHeader() + jwt));
    }

    @PostMapping("/api/refresh-token")
    @ApiOperation(value = "Atualizar token", notes = "Atualiza data de validade do token",
            authorizations = {@Authorization(value = AuthenticationTypeConstant.API)}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Token atualizado com sucesso."),
            @ApiResponse(code = 401, message = "Token informado é invalido")
    })
    public ResponseEntity<RetornoTokenDto> refresh() {
        String jwt = loginService.refreshToken(sessaoService.getUsuarioApiLogado());
        return ResponseEntity
                .ok()
                .header(X_AUTH_TOKEN_HEADER, jwt)
                .body(new RetornoTokenDto(AuthenticationType.API.getPrefixHeader() + jwt));
    }

    @GetMapping("/api/me")
    @ApiOperation(value = "Retorna informações usuário API", notes = "Retorna os dados do usuário API",
            authorizations = {@Authorization(value = AuthenticationTypeConstant.API)}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Token informado é invalido")
    })
    public ResponseEntity<UsuarioDto> apiMe() {
        ApiUserAuthenticatedDto auth = sessaoService.getUsuarioApiLogado();
        UsuarioDto usuarioCompleto = auth.getUsuario();
        return ResponseEntity.ok().body(UsuarioDto.builder()
                .permissoes(usuarioCompleto.getPermissoes())
                .login(usuarioCompleto.getLogin())
                .id(usuarioCompleto.getId())
                .build()
        );
    }
}

