package com.saga.ecommerce.web.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    DirectExchange directExchange(@Value("${exchanges.ecommerce}") String exchangeName) {
        return new DirectExchange(exchangeName, true, false, null);
    }

    @Bean
    Queue pedidoCriadoQueue(@Value("${queues.pedido-criado}") String queueName) {
        return new Queue(queueName, true, false, false, null);
    }

    @Bean
    Binding bindExchangeToQueue(Queue pedidoCriado, DirectExchange exchange, @Value("${exchanges.routing-key.ecommerce}") String routingKey) {
        return BindingBuilder.bind(pedidoCriado).to(exchange).with(routingKey);
    }

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReady(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}
