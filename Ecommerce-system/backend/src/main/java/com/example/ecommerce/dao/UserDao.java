package com.example.ecommerce.dao;

import com.example.ecommerce.dto.GoodsPriceParam;
import com.example.ecommerce.mbg.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rain
 * @date: 2020/6/19 21:07
 * @description:
 */
@Mapper
public interface UserDao {
    int InsertOrder(@Param("list") List<Order> list);

    int InsertOrderFromChart(@Param("list") List<Order> list);

    List<Goods> GetGoodsOrderByNumber(GoodsExample goodsExample);

    List<GoodsPriceParam> GetGoodsOrderByPriceDesc();

    List<GoodsPriceParam> GetGoodsOrderByPriceAsc();

    BigDecimal getPriceFromGood(@Param("GoodId")String GoodId);


}
