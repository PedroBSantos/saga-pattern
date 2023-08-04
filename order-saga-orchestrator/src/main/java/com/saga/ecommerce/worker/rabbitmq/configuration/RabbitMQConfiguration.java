package com.saga.ecommerce.worker.rabbitmq.configuration;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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
    Queue pedidoCriadoQueue(@Value("${queues.pedido-criado}") String queue) {
        return new Queue(queue, true, false, false, null);
    }

    @Bean
    Queue falhaRegistroEntregaPedidoQueue(@Value("${queues.entrega.falha-registro-entrega}") String queue) {
        return new Queue(queue, true, false, false, null);
    }

    @Bean
    Queue falhaOperacaoStockQueue(@Value("${queues.stock.falha-operacao}") String queue) {
        return new Queue(queue, true, false, false, null);
    }

    @Bean
    Binding bindExchangeToFalhaRegistroEntregaPedidoQueue(Queue falhaRegistroEntregaPedidoQueue, DirectExchange exchange,
            @Value("${exchanges.routing-key.falha-processar-entrega}") String routingKey) {
        return BindingBuilder.bind(falhaRegistroEntregaPedidoQueue).to(exchange).with(routingKey);
    }

    @Bean
    Binding bindExchangeToFalhaOperacaoStockQueue(Queue falhaOperacaoStockQueue, DirectExchange exchange,
            @Value("${exchanges.routing-key.falha-processar-stock}") String routingKey) {
        return BindingBuilder.bind(falhaOperacaoStockQueue).to(exchange).with(routingKey);
    }

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter) {
        var factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReady(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}
