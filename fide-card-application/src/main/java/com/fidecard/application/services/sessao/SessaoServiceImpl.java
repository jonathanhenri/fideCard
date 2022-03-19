package com.fidecard.application.services.sessao;

import br.com.oobj.cache.CacheHandler;
import br.com.oobj.canhoto.digital.model.sistema.service.SistemaService;
import br.com.oobj.canhoto.digital.model.usuario.factory.UsuarioDtoFactory;
import com.fidecard.application.services.usuario.UsuarioService;
import com.fidecard.common.seguranca.ApiUserAuthenticatedDto;
import com.fidecard.common.seguranca.UserAuthenticatedDto;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.function.Function;

/**
 * Controla as sessões do usuário, tanto API como IAPI
 *
 */
@Service
public class SessaoServiceImpl implements SessaoService {

    private static final Logger LOG = LoggerFactory.getLogger(SessaoServiceImpl.class);

    private static final String CACHE_USUARIO_API = "e-canhoto-usuario-api";
    private static final String CACHE_USUARIO_IAPI = "e-canhoto-usuario-iapi";

    private final UsuarioService usuarioService;
    private final SistemaService sistemaService;
    private final CacheHandler cache;

    @Autowired
    public SessaoServiceImpl(UsuarioService usuarioService, SistemaService sistemaService, CacheHandler cache) {
        this.usuarioService = usuarioService;
        this.sistemaService = sistemaService;
        this.cache = cache;
    }

    @Override
    public ApiUserAuthenticatedDto getUsuarioApiLogado() {
        final UserAuthenticatedDto user = getUsuarioLogado();
        if (user instanceof ApiUserAuthenticatedDto) {
            return (ApiUserAuthenticatedDto) user;
        }
        throw new IllegalStateException("Usuario logado não é API");
    }


    @Override
    public ApiUserAuthenticatedDto getUsuarioApiCache(String cpf) {
        return getUsuarioCache(CACHE_USUARIO_API, cpf, identificao -> {
            ApiUserAuthenticatedDto usuario = Optional.ofNullable(usuarioService.findByCpf(identificao))
                    .map(UsuarioDtoFactory::build)
                    .map(ApiUserAuthenticatedDto::new)
                    .orElse(null);
            if (usuario != null) {
                cache.put(CACHE_USUARIO_API, usuario.getIdentificacao(), usuario);
            }
            return usuario;
        });
    }

    @Override
    public void limparCacheUsuarioLogado() {
        UserAuthenticatedDto usuario = getUsuarioLogado();
        if (usuario == null) {
            return;
        }
        if (usuario instanceof ApiUserAuthenticatedDto) {
            limparCacheUsuarioApi(usuario.getIdentificacao());
        }
    }

    @Override
    public void limparCacheUsuarioApi(String cpf) {
        cache.invalidate(CACHE_USUARIO_API, cpf);
    }


    private UserAuthenticatedDto getUsuarioLogado() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserAuthenticatedDto) {
            return ((UserAuthenticatedDto) authentication.getPrincipal());
        }
        throw new IllegalStateException("Nenhum usuario logado");
    }

    @SuppressWarnings("unchecked")
    private <D extends UserAuthenticatedDto> D getUsuarioCache(String prefix, String identificacao,
                                                               Function<String, D> loadUser) {
        if (StringUtils.isEmpty(identificacao)) {
            return null;
        }
        Object usuario = null;
        try {
            usuario = cache.get(prefix, identificacao);
        } catch (SerializationException e) {
            LOG.error("Houve um problema de serialização para a chave '" + prefix + ":" + identificacao + "'" +
                    ", vou atualizar o cache deste registro.", e);
        }
        if (usuario == null) {
            return loadUser.apply(identificacao);
        }
        return (D) usuario;
    }

}
