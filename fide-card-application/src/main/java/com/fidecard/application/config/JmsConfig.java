package com.fidecard.application.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import javax.jms.ConnectionFactory;
import javax.jms.QueueConnectionFactory;

@Configuration
@EnableJms
@EnableAsync
@EnableScheduling
@Profile("!test")
public class JmsConfig {
	
	@Value("${activemq.timeout:5000}")
	private int timeoutConexao;
	
	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;
	
	@Value("${spring.activemq.user}")
	private String user;
	
	@Value("${spring.activemq.password}")
	private String password;
	
	@Primary
	@Bean("jmsConnectionFactory")
	public QueueConnectionFactory jmsConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(user, password, brokerUrl);
		factory.setTrustAllPackages(true);
		factory.setConnectResponseTimeout(timeoutConexao);
		factory.setSendTimeout(timeoutConexao);
		return factory;
	}
	
	@Primary
	@Bean("jmsTemplate")
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(jmsConnectionFactory());
		return jmsTemplate;
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory alteracaoCertificadoListenerContainerFactory(
			ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setPubSubDomain(true);
		return factory;
	}
	
}
