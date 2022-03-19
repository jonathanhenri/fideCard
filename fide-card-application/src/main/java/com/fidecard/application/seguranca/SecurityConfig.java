package com.fidecard.application.seguranca;

import br.com.oobj.canhoto.digital.config.security.authentication.AuthenticationType;
import br.com.oobj.canhoto.digital.config.security.handles.DefaultAccessDeniedHandler;
import br.com.oobj.canhoto.digital.config.security.handles.DefaultAuthenticationEntryPoint;
import br.com.oobj.canhoto.digital.config.security.jwt.JwtConfigurer;
import br.com.oobj.canhoto.digital.config.security.secretkey.SecretKeyConfigurer;
import br.com.oobj.canhoto.digital.controllers.autenticacao.AuthController;
import br.com.oobj.canhoto.digital.controllers.usuario.UsuarioController;
import br.com.oobj.canhoto.digital.seguranca.api.ApiJwtProvider;
import br.com.oobj.canhoto.digital.seguranca.iapi.IApiSecretKeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuração Spring Security
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final IApiSecretKeyProvider secretKeyProvider;
    private final ApiJwtProvider jwtProvider;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SecurityConfig(IApiSecretKeyProvider secretKeyProvider, ApiJwtProvider jwtProvider,
						  DefaultAuthenticationEntryPoint authenticationEntryPoint,
						  DefaultAccessDeniedHandler accessDeniedHandler) {
        super(true);
        this.jwtProvider = jwtProvider;
        this.secretKeyProvider = secretKeyProvider;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();

        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and();

        http.anonymous().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, AuthController.LOGIN).permitAll()
                .antMatchers(HttpMethod.POST, UsuarioController.CADASTRAR_USUARIO).permitAll()
                .antMatchers(HttpMethod.POST, UsuarioController.REDEFINIR_SENHA).permitAll()
                .antMatchers(AuthenticationType.API.getBaseUrl())
                .hasRole(AuthenticationType.API.getRole().getNome())
                .antMatchers(AuthenticationType.IAPI.getBaseUrl())
                .hasRole(AuthenticationType.IAPI.getRole().getNome());

        http.apply(new SecretKeyConfigurer(secretKeyProvider));
        http.apply(new JwtConfigurer(jwtProvider));
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
