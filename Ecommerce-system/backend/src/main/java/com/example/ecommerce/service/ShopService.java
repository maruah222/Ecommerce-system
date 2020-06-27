package com.example.ecommerce.service;

import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.dto.AddSkuParam;
import com.example.ecommerce.dto.CommentParam;
import com.example.ecommerce.dto.GoodsParam;
import com.example.ecommerce.mbg.model.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
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
    CommonResult WithdrawGoodsByGoodId(String GoodId);
    void SendDelayMessageOverTime(String ShopId);

    CommonResult CancelRegister(String ShopId);

    List<OrderReturn> getNeedVerifyReturnByShopId(String ShopId,int pageNum,int pageSize);
    @Transactional
    CommonResult VerifyReturnSuccess(String OrderId);
    @Transactional
    CommonResult VerifyReturnFailed(String OrderId);
    CommonResult VerifyReturnSendEmail(String userId, int num);

    Shop getInformationByShopId(String ShopId);

    CommonResult modifyShopInformation(String sellername,String password,String shopaddress,String shopname, String telephone,String shopId);
    List<GoodSku> getSkuByGoodId(String GoodId,int pageNum,int pageSize);

    CommonResult DeleteSkuBySkuId(int skuId);
    CommonResult AddSkuByGoodId(AddSkuParam addSkuParam);
    CommonResult AddNumberInSku(int skuId,int number);
    CommonResult ModifySku(int skuId,BigDecimal price, BigDecimal vipprice,String picture);//修改商品sku的价格和进货量

    List<Goods> getDownGoodsByShopId(String ShopId,int pageNum,int pageSize);
    CommonResult downGoodsByGoodId(String GoodId,String ShopId);

    CommonResult ApplyDownGoodsUp(String GoodId);

    //查看自家商品的订单记录
    XSSFWorkbook showOrderExcelByShopId(String ShopId);



    List<Order> getOrdernotGet(String ShopId,int pageNum,int pageSize);


   /* CommonResult AddSkuByGoodId(String GoodId,int SkuId,int num,BigDecimal price, BigDecimal vipprice,int Left_number,String picture,String Attribute);
    CommonResult AddNumberInSku(int skuid,int num);
    CommonResult ModifySku(int SKUId, String GoodId, BigDecimal price, BigDecimal vipprice);
    List<Goods> GetGoodByShopId(String ShopId,int pageNum, int pageSize);
*/

}
