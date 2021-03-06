package com.swayam.demo.activemq.config;

import javax.jms.ConnectionFactory;
import javax.jms.MessageListener;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import com.swayam.demo.activemq.service.sub.JmsMessageConsumer;

@Configuration
@EnableJms
@ComponentScan("com.swayam.demo.activemq")
@PropertySource("classpath:application.properties")
public class ActiveMQConfig {

    @Autowired
    private Environment environment;

    @Bean(destroyMethod = "stop")
    public ConnectionFactory connectionFactory() {
	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	connectionFactory.setBrokerURL(environment.getProperty("jms.brokerUrl"));
	PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
	pooledConnectionFactory.setConnectionFactory(connectionFactory);
	return pooledConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory, DestinationResolver destinationResolver) {
	DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	factory.setConnectionFactory(connectionFactory);
	factory.setDestinationResolver(destinationResolver);
	factory.setConcurrency("3-10");
	return factory;
    }

    @Bean
    public DestinationResolver destinationResolver() {
	return new DynamicDestinationResolver();
    }

    @Bean
    public MessageListenerContainer defaultMessageListenerContainer(ConnectionFactory connectionFactory, MessageListener messageListener) {
	DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
	defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
	defaultMessageListenerContainer.setDestinationName("bank-details");
	defaultMessageListenerContainer.setMessageListener(messageListener);
	return defaultMessageListenerContainer;
    }

    @Bean
    public MessageListener messageListener() {
	return new JmsMessageConsumer();
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
	JmsTemplate jmsTemplate = new JmsTemplate();
	jmsTemplate.setConnectionFactory(connectionFactory);
	return jmsTemplate;
    }

}
