package com.example.ecommerce.controller;

import com.example.ecommerce.common.api.CommonPage;
import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.dto.GoodsParam;
import com.example.ecommerce.mbg.model.*;
import com.example.ecommerce.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: rain
 * @date: 2020/6/5 17:27
 * @description:
 */
@Api(tags = "ShopController",description = "商家的操作接口")
@Controller
@RequestMapping("/Shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("商家的注册")
    @RequestMapping(value = "/Sellerregister",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult register(@RequestParam String ShopId,
                                 @RequestParam String password,
                                 @RequestParam String Shopname,
                                 @RequestParam String Sellername,
                                 @RequestParam String address,
                                 @RequestParam String sellertelephone)
    {
        return shopService.SellerRegister(ShopId,password, Shopname,Sellername,address,sellertelephone);
    }

    @ApiOperation("商家登录")
    @RequestMapping(value = "/Sellerlogin",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult login(@RequestParam String username,
                              @RequestParam String password)
    {
        String token = shopService.SellerLogin(username, password);
        if(token == null)
        {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("tokenhead",tokenHead);

        return CommonResult.success(map);
    }

    @ApiOperation("商家申请上架商品")
    @RequestMapping(value = "/ApplyGoodsOn",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult ApplyGoodsOn(@RequestBody GoodsParam goodsParam,BindingResult bindingResult)
    {
        return shopService.ApplyGoodsUp(goodsParam);
    }

    @ApiOperation("商家获取shop信息")
    @RequestMapping(value = "/GetInformationByShopId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetInformationByShopId(@RequestParam String ShopId)
    {
        Shop shop=shopService.getInformationByShopId(ShopId);
        return CommonResult.success(shop,"获取成功");
    }

    @ApiOperation("商家修改shop信息")
    @RequestMapping(value = "/ModifyShopInformation",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult ModifyShopInformation(@RequestParam String sellername,
                                              @RequestParam String password,
                                              @RequestParam String shopaddress,
                                              @RequestParam String shopname,
                                              @RequestParam String telephone,
                                              @RequestParam String shopId)
    {

        return shopService.modifyShopInformation(sellername,password,shopaddress,shopname,telephone,shopId);
    }


    @ApiOperation("商家获取退货退款申请")
    @RequestMapping(value = "/getNeedVerifyReturn",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getNeedVerifyReturn(@RequestParam String ShopId,
                                            @RequestParam(value = "pageNum") @ApiParam("页码") int pageNum,
                                            @RequestParam(value = "pageSize") @ApiParam("页面大小") int pageSize)
    {
        List<OrderReturn> orderReturns=shopService.getNeedVerifyReturnByShopId(ShopId,pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(orderReturns));
    }

    @ApiOperation("审核退货退款的申请")
    @RequestMapping(value = "/VerifyReturn",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult VerifyReturn(@RequestParam String OrderId,@RequestParam int value)
    {
        if(value==1)
        {
            return shopService.VerifyReturnSuccess(OrderId);
        }
        else if(value==2)
        {
            return shopService.VerifyReturnFailed(OrderId);
        }
        else
        {
            return CommonResult.failed("审核啊，你在干嘛？？");
        }
    }


    @ApiOperation("根据商品Id获取sku")
    @RequestMapping(value = "/getSkuByGoodId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getSkuByGoodId(@RequestParam String GoodId,
                                            @RequestParam(value = "pageNum") @ApiParam("页码") int pageNum,
                                            @RequestParam(value = "pageSize") @ApiParam("页面大小") int pageSize)
    {
        List<GoodSku> list=shopService.getSkuByGoodId(GoodId,pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }


    @ApiOperation("根据skuid删除sku")
    @RequestMapping(value = "/DeleteSkuBySkuId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult DeleteSkuBySkuId(@RequestParam int skuId)
    {
        return shopService.DeleteSkuBySkuId(skuId);
    }

    @ApiOperation("商家通过GoodID添加商品SKU")
    @RequestMapping(value = "/AddSkuByGoodId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult AddSkuByGoodId(@RequestParam String GoodId,
                                       @RequestParam int SkuId,
                                       @RequestParam int num,
                                       @RequestParam BigDecimal price,
                                       @RequestParam BigDecimal vipprice,
                                       @RequestParam int Left_number,
                                       @RequestParam String picture,
                                       @RequestParam String Attribute)
    {
        return shopService.AddSkuByGoodId(GoodId,SkuId,num,price,vipprice,Left_number,picture,Attribute);
    }


    @ApiOperation("商家在商品SKU增加库存")
    @RequestMapping(value = "/AddNumberInSku",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult AddNumberInSku(@RequestParam  String skuid,int num)
    {
        return AddNumberInSku(skuid,num);
    }

    @ApiOperation("商家修改商品SKU")
    @RequestMapping(value = "/ ModifySku",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult ModifySku(@RequestParam  int SkuId,
                                  @RequestParam String GoodId,
                                  @RequestParam BigDecimal price,
                                  @RequestParam BigDecimal vipprice)
    {
        return shopService.ModifySku(SkuId,GoodId,price,vipprice);
    }

    @ApiOperation("商家查看商品信息")
    @RequestMapping(value = "/GetGoodByShopId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult ModifySku(@RequestParam  String ShopId,
                                  @RequestParam int pageNum,
                                  @RequestParam int pageSize)
    {
         List<Goods> goods= shopService.GetGoodByShopId(ShopId,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(goods));
    }

}
