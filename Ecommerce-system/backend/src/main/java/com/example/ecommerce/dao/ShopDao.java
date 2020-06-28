package com.example.ecommerce.dao;

import com.example.ecommerce.mbg.model.Goods;
import com.example.ecommerce.mbg.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: rain
 * @date: 2020/6/21 22:33
 * @description:
 */
public interface ShopDao {

    List<Order> selectOrderByGoodId(@Param("list") List<Goods> list);

    List<Order> selectnotGetOrderByGoodId(@Param("list") List<Goods> list);
}
