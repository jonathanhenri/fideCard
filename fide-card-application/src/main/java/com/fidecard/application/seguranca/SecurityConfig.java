package com.fidecard.application.seguranca;

import com.fidecard.application.controllers.autenticacao.AuthController;
import com.fidecard.application.controllers.usuario.UsuarioController;
import com.fidecard.application.seguranca.api.ApiJwtProvider;
import com.fidecard.application.seguranca.authentication.AuthenticationType;
import com.fidecard.application.seguranca.handles.DefaultAccessDeniedHandler;
import com.fidecard.application.seguranca.handles.DefaultAuthenticationEntryPoint;
import com.fidecard.application.seguranca.jwt.JwtConfigurer;
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
    private final ApiJwtProvider jwtProvider;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    
    @Autowired
    public SecurityConfig(ApiJwtProvider jwtProvider,
                          DefaultAuthenticationEntryPoint authenticationEntryPoint,
                          DefaultAccessDeniedHandler accessDeniedHandler) {
        super(true);
        this.jwtProvider = jwtProvider;
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
                .antMatchers(HttpMethod.POST, UsuarioController.URL_CADASTRAR).permitAll()
//                .antMatchers(HttpMethod.POST, UsuarioController.REDEFINIR_SENHA).permitAll()
                .antMatchers(AuthenticationType.API.getBaseUrl())
                .hasRole(AuthenticationType.API.getRole().getNome());
        
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
