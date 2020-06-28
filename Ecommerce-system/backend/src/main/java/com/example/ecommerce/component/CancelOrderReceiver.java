package com.example.ecommerce.component;

import com.example.ecommerce.service.UserrService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: rain
 * @date: 2020/6/19 21:38
 * @description:
 */
@Component
@RabbitListener(queues = "ecommerce.user.ordercancel")
public class CancelOrderReceiver {

    @Autowired
    private UserrService userrService;

    @RabbitHandler
    public void handle(String[] orderId_userId){
        userrService.cancelOverTimeOrder(orderId_userId);
    }
}
