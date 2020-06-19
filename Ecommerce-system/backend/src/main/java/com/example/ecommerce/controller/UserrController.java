package com.example.ecommerce.controller;

import com.example.ecommerce.common.api.CommonPage;
import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.dto.LoginParam;
import com.example.ecommerce.mbg.mapper.ChartMapper;
import com.example.ecommerce.mbg.model.*;
import com.example.ecommerce.service.UserrService;
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
 * @date: 2020/6/1 12:04
 * @description:
 */
@Api(tags = "UserController",description = "普通用户的操作接口")
@Controller
@RequestMapping("/User")
public class UserrController {

    @Autowired
    private UserrService userrService;

    @Autowired(required = false)
    private ChartMapper chartMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("普通用户的注册")
    @RequestMapping(value = "/Userregister",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone)
    {
        return userrService.register(username, password, telephone);
    }

    @ApiOperation("普通用户登录")
    @RequestMapping(value = "/Userlogin",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody LoginParam loginParam,
                              BindingResult bindingResult)
    {
        String token = userrService.login(loginParam.getUsername(), loginParam.getPassword());
        if(token == null)
        {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("tokenhead",tokenHead);

        return CommonResult.success(map);
    }

    @ApiOperation("用户添加购物车")
    @RequestMapping(value = "/AddChart",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult login(@RequestParam String GoodId,
                              @RequestParam int number,
                              @RequestParam BigDecimal price,
                              @RequestParam String Attribute,
                              HttpServletRequest request)
    {
        userrService.AddChart(GoodId, number, price, Attribute, request);
        return CommonResult.success(GoodId,"添加成功");
    }

    @ApiOperation("根据用户名获取购物车")
    @RequestMapping(value = "/GetAllChartByUserId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAllChartByUserId(@RequestParam(value = "pageNum") @ApiParam("页码") int pageNum,
                                            @RequestParam(value = "pageSize") @ApiParam("页面大小") int pageSize,
                                            HttpServletRequest request)
    {
        List<Chart> charts = userrService.getAllChartByUserId(pageNum, pageSize, request);
        return CommonResult.success(CommonPage.restPage(charts));
    }

    @ApiOperation("更新购物车中商品数量")
    @RequestMapping(value = "/UpdateNumInChart",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult UpdateNumInChart(@RequestParam String chartId,
                                         @RequestParam String GoodId,
                                         @RequestParam String Attribute,
                                         @RequestParam int num)
    {
        userrService.updateNumInChart(chartId,GoodId,Attribute,num);
        return CommonResult.success("购物车商品数量增加成功");
    }

    @ApiOperation("删除购物车的商品")
    @RequestMapping(value = "/DeleteChartByGoodId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult DeleteChartByGoodId(@RequestParam String userId,
                                         @RequestParam String GoodId)
    {
        userrService.deleteChartByGoodId(userId,GoodId);
        return CommonResult.success("购物车商品删除成功");
    }

    @ApiOperation("清空购物车的商品")
    @RequestMapping(value = "/ClearChart",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult ClearChart(@RequestParam String userId)
    {
        userrService.clearChart(userId);
        return CommonResult.success("购物车清空成功");
    }

    @ApiOperation("查看所有商家")
    @RequestMapping(value = "/GetAllShop",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetAllShop(@RequestParam int pageNum,
                                    @RequestParam int pageSize)
    {
        List<Shop> shops = userrService.getAllShop(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(shops));
    }

    @ApiOperation("查看所有商品")
    @RequestMapping(value = "/GetAllGoods",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetAllGoods(@RequestParam int pageNum,
                                    @RequestParam int pageSize)
    {
        List<Goods> goods = userrService.getAllGoods(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(goods));
    }

    @ApiOperation("查看所有商品分类")
    @RequestMapping(value = "/GetAllGoodsCategory",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetAllGoodsCategory(@RequestParam int pageNum,
                                            @RequestParam int pageSize)
    {
        List<GoodsCategory> goods = userrService.getAllGoodsCategory(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(goods));
    }

    //查看商品，和商家

    @ApiOperation("根据商家Id获取商品")
    @RequestMapping(value = "/GetGoodsByShopId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetGoodsByShopId(@RequestParam String shopId,
                                         @RequestParam int pageNum,
                                         @RequestParam int pageSize)
    {
        List<Goods> goods=userrService.getGoodsByShopId(shopId,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(goods));
    }

    @ApiOperation("根据用户名获取订单")
    @RequestMapping(value = "/GetAllOrderByUserId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetAllOrderByUserId(@RequestParam int pageNum,
                                            @RequestParam int pageSize,
                                            HttpServletRequest request)
    {
        List<Order> orders= userrService.getAllOrderByUserId(pageNum, pageSize, request);
        return CommonResult.success(CommonPage.restPage(orders));
    }

    @ApiOperation("根据订单Id获取订单信息")
    @RequestMapping(value = "/GetOrderDetail",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetOrderDetail(@RequestParam String orderId)
    {
        Order order=userrService.getOrderDetail(orderId);
        return CommonResult.success(order,"获取成功");
    }

    @ApiOperation("用户确认收货")
    @RequestMapping(value = "/GetGoodsSuccess",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetGoodsSuccess(@RequestParam String orderId)
    {
        return userrService.GetGoodsSuccess(orderId);
    }


    @ApiOperation("用户申请退货退款")
    @RequestMapping(value = "/GoodsReturnApply",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GoodsReturnApply(@RequestParam String orderId)
    {
        return userrService.GoodsReturnApply(orderId);
    }


}
