package com.example.ecommerce.service;

import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.dto.GoodsParam;
import com.example.ecommerce.mbg.model.GoodSku;
import com.example.ecommerce.mbg.model.Goods;
import com.example.ecommerce.mbg.model.Order;
import com.example.ecommerce.mbg.model.OrderReturn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: rain
 * @date: 2020/6/4 21:48
 * @description:
 */

public interface ShopService {

    @Transactional
    CommonResult SellerRegister(String ShopId,String password, String Shopname,String Sellername,String address,String sellertelephone);

    String SellerLogin(String Sellername,String Sellerpassword);

    @Transactional
    CommonResult ApplyGoodsUp(GoodsParam goodsParam);

    void SendDelayMessageOverTime(String ShopId);

    CommonResult CancelRegister(String ShopId);

    List<Order> getNeedVerifyReturn(int pageNum,int pageSize);
    @Transactional
    CommonResult VerifyReturnSuccess(String OrderId);
    @Transactional
    CommonResult VerifyReturnFailed(String OrderId);
    CommonResult VerifyReturnSendEmail(String userId, int num);
}
