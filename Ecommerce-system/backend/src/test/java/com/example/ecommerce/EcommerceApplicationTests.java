package com.example.ecommerce;

import com.example.ecommerce.dao.AddSkuDao;
import com.example.ecommerce.dao.ShopDao;
import com.example.ecommerce.dao.UserDao;
import com.example.ecommerce.mbg.mapper.GoodsMapper;
import com.example.ecommerce.mbg.mapper.UserrMapper;
import com.example.ecommerce.mbg.model.*;
import com.example.ecommerce.service.ManagerService;
import com.example.ecommerce.service.UserrService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@SpringBootTest
public class EcommerceApplicationTests {

    @Autowired(required = false)
    public UserDao userDao;

    @Autowired(required = false)
    private ShopDao shopDao;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired(required = false)
    private GoodsMapper goodsMapper;



    @Test
    public void contextLoads() {

        /*GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopidEqualTo("123456");

        List<Goods> list = goodsMapper.selectByExample(goodsExample);

        List<Order> orders = shopDao.selectOrderByGoodId(list);*/

      /*  SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1005131042@qq.com");
        message.setTo("1005131042@qq.com");
        message.setSubject("这是标题");
        message.setText("这是内容");
        javaMailSender.send(message);*/
    }
/*
    @Test
    void contextLoads() {

        List<Order> list = new ArrayList<>();
        Order order = new Order();

        BigDecimal a =new BigDecimal("123.33");

        order.setOrderid("796825");
        order.setComment("未评论");
        order.setMoney(a);
        order.setPrice(a);
        order.setNumber(10);
        order.setGoodid("1592550242");
        order.setPaytime(new Date());
        order.setState(1);
        order.setUserid("999");


        list.add(order);

        System.out.println(list.get(0));

        userDao.InsertOrderFromChart(list);
    }*/

}
