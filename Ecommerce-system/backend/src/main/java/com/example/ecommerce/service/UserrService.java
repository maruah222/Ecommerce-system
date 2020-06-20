package com.example.ecommerce.service;

import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.dto.GoodDetailParam;
import com.example.ecommerce.mbg.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rain
 * @date: 2020/6/1 11:47
 * @description:
 */

public interface UserrService {

    CommonResult register(String username,String password,String telephone);

    String login(String username,String password);

    //购物车部分
    @Transactional
    CommonResult AddChart(String GoodId, int number, BigDecimal price,String Attribute, HttpServletRequest request);
    List<Chart> getAllChartByUserId(int pageNum,int pageSize,HttpServletRequest request);
    CommonResult updateNumInChart(String chartId,String GoodId,String Attribute,int num);
    CommonResult deleteChartByGoodId(String chartId);
    CommonResult clearChart(String userId);


    //查看商品，和商家
    List<Goods> getAllGoods(int pageNum, int pageSize);

    List<Goods> getGoodsByShopId(String shopid,int pageNum, int pageSize);

    List<GoodsCategory> getAllGoodsCategory(int pageNum,int pageSize);

    GoodDetailParam getGoodsDetailsByGoodId(String GoodId);

    List<Shop> getAllShop(int pageNum,int pageSize);

    Shop getShopByShopId(String ShopId);
    //订单
    List<Order> getAllOrderByUserId(int pageNum, int pageSize, HttpServletRequest request);

    Order getOrderDetail(String orderId);

    //确认收货
    CommonResult GetGoodsSuccess(String OrderId);

    CommonResult GoodsReturnApply(String OrderId,String reason, HttpServletRequest request);

    //提交订单，评论，购买
    CommonResult ConfirmOrderByChart(List<Chart> charts, HttpServletRequest request);
    CommonResult cancelOverTimeOrder(String[] orderId_userId);
    CommonResult sendOrderCancelEmail(String userId,String orderId);

    CommonResult AddComment(String orderId,String message, HttpServletRequest request);
    List<Order> getNeedCommentByUserId(int pageNum, int pageSize, HttpServletRequest request);


    Userr getUserInformationByUserId(String UserId);
    CommonResult modifyUserInformation(String UserId);
}

