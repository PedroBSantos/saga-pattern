package com.saga.ecommerce.worker.rabbitmq.consumers;

import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.saga.ecommerce.core.compensators.ErrorStockOperationCompensator;
import com.saga.ecommerce.core.orchestrator.step_error_observers.stock.FailedOrderProcessed;

@Component
public class ErrorStockOperationConsumer {

    private Logger logger;
    private ErrorStockOperationCompensator compensator;

    public ErrorStockOperationConsumer(@Autowired ErrorStockOperationCompensator compensator) {
        this.compensator = compensator;
        this.logger = Logger.getLogger(ErrorStockOperationConsumer.class.getSimpleName());
    }

    @RabbitListener(queues = "stock.v1.falha-operacao", containerFactory = "rabbitListenerContainerFactory", ackMode = "MANUAL")
    public void onErrorStockOperation(
            FailedOrderProcessed failedOrder,
            Channel channel,
            @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        this.logger.info("Compensating failure on stock operation. Order id " + failedOrder.getOrderId());
        this.compensator.compensate(failedOrder);
        channel.basicAck(tag, false);
    }
}
