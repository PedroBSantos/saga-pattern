package com.saga.ecommerce.worker.rabbitmq.consumers;

import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.saga.ecommerce.core.orchestrator.OrderCreatedSagaOrchestrator;
import com.saga.ecommerce.core.orchestrator.inputs.OrderCreated;

@Component
public class OrderCreatedConsumer {

    private Logger logger;
    private OrderCreatedSagaOrchestrator orchestrator;

    public OrderCreatedConsumer(@Autowired OrderCreatedSagaOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
        this.logger = Logger.getLogger(OrderCreatedConsumer.class.getSimpleName());
    }

    @RabbitListener(queues = "pedido.v1.pedido-criado", containerFactory = "rabbitListenerContainerFactory", ackMode = "MANUAL")
    public void onOrderCreated(OrderCreated orderCreated, Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        this.logger.info("Processing order created with id " + orderCreated.getId());
        this.orchestrator.orchestrate(orderCreated);
        channel.basicAck(tag, false);
    }
}
