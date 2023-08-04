package com.saga.ecommerce.worker.rabbitmq.publishers;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.saga.ecommerce.core.orchestrator.step_error_observers.stock.ErrorStockOperationObserver;
import com.saga.ecommerce.core.orchestrator.step_error_observers.stock.FailedOrderProcessed;

@Component
public class ErrorStockOperationPublisher implements ErrorStockOperationObserver {

    private RabbitTemplate rabbitTemplate;
    private String exchange;
    private String routingKey;
    private Gson jsonSerializer;

    public ErrorStockOperationPublisher(
        @Autowired RabbitTemplate rabbitTemplate, 
        @Value("${exchanges.ecommerce}") String exchange, 
        @Value("${exchanges.routing-key.falha-processar-stock}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.jsonSerializer = new Gson();
    }

    @Override
    public void update(FailedOrderProcessed failedOrderProcessed) {
        this.sendMessageToFalhaProcessarStockQueue(failedOrderProcessed);
    }

    public void sendMessageToFalhaProcessarStockQueue(FailedOrderProcessed failedOrderProcessed) {
        var messageOnJsonFormat = this.convertOrderToJson(failedOrderProcessed);
        var rabbitMqMessage = new Message(messageOnJsonFormat.getBytes());
        this.rabbitTemplate.send(this.exchange, this.routingKey, rabbitMqMessage);
    }

    private String convertOrderToJson(FailedOrderProcessed failedOrderProcessed) {
        return this.jsonSerializer.toJson(failedOrderProcessed);
    }
}
