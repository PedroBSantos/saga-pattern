package com.saga.ecommerce.worker.rabbitmq.publishers;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.saga.ecommerce.core.orchestrator.inputs.OrderCreated;
import com.saga.ecommerce.core.orchestrator.step_error_observers.delivery.ErrorDeliveryRegistrationObserver;

@Component
public class ErrorDeliveryRegistrationPublisher implements ErrorDeliveryRegistrationObserver {

    private RabbitTemplate rabbitTemplate;
    private String exchange;
    private String routingKey;
    private Gson jsonSerializer;

    public ErrorDeliveryRegistrationPublisher(
        @Autowired RabbitTemplate rabbitTemplate, 
        @Value("${exchanges.ecommerce}") String exchange, 
        @Value("${exchanges.routing-key.falha-processar-entrega}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.jsonSerializer = new Gson();
    }

    @Override
    public void update(OrderCreated orderCreated) {
        this.sendMessageToFalhaRegistroEntregaQueue(orderCreated);
    }

    public void sendMessageToFalhaRegistroEntregaQueue(OrderCreated orderCreated) {
        var messageOnJsonFormat = this.convertOrderToJson(orderCreated);
        var rabbitMqMessage = new Message(messageOnJsonFormat.getBytes());
        this.rabbitTemplate.send(this.exchange, this.routingKey, rabbitMqMessage);
    }

    private String convertOrderToJson(OrderCreated orderCreated) {
        return this.jsonSerializer.toJson(orderCreated);
    }
}
