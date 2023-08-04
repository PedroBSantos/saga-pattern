package com.saga.ecommerce.worker.rabbitmq.consumers;

import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.saga.ecommerce.core.compensators.ErrorDeliveryCompensator;
import com.saga.ecommerce.core.orchestrator.inputs.OrderCreated;

@Component
public class ErrorDeliveryRegistrationConsumer {

    private Logger logger;
    private ErrorDeliveryCompensator compensator;

    public ErrorDeliveryRegistrationConsumer(@Autowired ErrorDeliveryCompensator compensator) {
        this.compensator = compensator;
        this.logger = Logger.getLogger(ErrorDeliveryRegistrationConsumer.class.getSimpleName());
    }

    @RabbitListener(queues = "entrega.v1.falha-registro-entrega", containerFactory = "rabbitListenerContainerFactory", ackMode = "MANUAL")
    public void onErrorDeliveryRegistration(
            OrderCreated orderCreated,
            Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        this.logger.info("Compensating failure on delivery order registration. Order id " + orderCreated.getId());
        this.compensator.compensate(orderCreated);
        channel.basicAck(tag, false);
    }
}
