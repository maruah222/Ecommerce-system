package com.example.ecommerce.component;

import com.example.ecommerce.dto.QueueEnum;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: rain
 * @date: 2020/6/19 21:37
 * @description:
 */
@Component
public class CancelOrderSender {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void sendMessage(String[] orderId_userId,Long delayTimes)
    {
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_ORDER_CANCEL_TTL.getExchange(), QueueEnum.QUEUE_ORDER_CANCEL_TTL.getRouteKey(), orderId_userId, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                return message;
            }
        });
    }
}
