package com.fidecard.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe responsavel por efetuar o bootstrap da aplicacao.
 */
@Configuration
@EnableSpringDataWebSupport
@EnableTransactionManagement
@EntityScan(basePackages = { "com.fidecard.application.**" })
@EnableJpaRepositories(basePackages = { "com.fidecard.application.**" })
public class RepositoryConfig {

}
