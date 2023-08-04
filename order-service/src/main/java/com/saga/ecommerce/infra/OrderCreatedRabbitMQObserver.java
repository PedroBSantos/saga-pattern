package com.saga.ecommerce.infra;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.saga.ecommerce.core.api.OrderCreatedObserver;
import com.saga.ecommerce.core.api.events.OrderCreatedEvent;

@Service
public class OrderCreatedRabbitMQObserver implements OrderCreatedObserver {

    private RabbitTemplate rabbitTemplate;
    private String exchange;
    private String routingKey;
    private Gson jsonSerializer;

    public OrderCreatedRabbitMQObserver(
            @Autowired RabbitTemplate rabbitTemplate,
            @Value("${exchanges.ecommerce}") String exchange,
            @Value("${exchanges.routing-key.ecommerce}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.jsonSerializer = new Gson();
    }

    @Override
    public void update(OrderCreatedEvent orderCreatedEvent) {
        this.sendMessageToCreatedOrderQueue(orderCreatedEvent);
    }

    public void sendMessageToCreatedOrderQueue(OrderCreatedEvent orderCreatedEvent) {
        var messageOnJsonFormat = this.convertOrderToJson(orderCreatedEvent);
        var rabbitMqMessage = new Message(messageOnJsonFormat.getBytes());
        this.rabbitTemplate.send(this.exchange, this.routingKey, rabbitMqMessage);
    }

    private String convertOrderToJson(OrderCreatedEvent orderCreatedEvent) {
        return this.jsonSerializer.toJson(orderCreatedEvent);
    }
}
