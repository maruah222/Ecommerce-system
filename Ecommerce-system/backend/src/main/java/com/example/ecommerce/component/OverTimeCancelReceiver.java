package com.example.ecommerce.component;

import com.example.ecommerce.mbg.model.Manager;
import com.example.ecommerce.service.ManagerService;
import com.example.ecommerce.service.ShopService;
import com.example.ecommerce.service.UserrService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: rain
 * @date: 2020/6/7 14:43
 * @description:
 */
@Component
@RabbitListener(queues = "ecommerce.shop.cancel")
public class OverTimeCancelReceiver {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ManagerService managerService;

    @RabbitHandler
    public void handle(String ShopId)
    {
        //商家注册超过12小时未审核，自动取消

        shopService.CancelRegister(ShopId);
    }


}
