package com.example.ecommerce.controller;

import com.example.ecommerce.common.api.CommonPage;
import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.dto.ChartsParam;
import com.example.ecommerce.dto.CommentParam;
import com.example.ecommerce.dto.GoodDetailParam;
import com.example.ecommerce.dto.LoginParam;
import com.example.ecommerce.mbg.mapper.ChartMapper;
import com.example.ecommerce.mbg.model.*;
import com.example.ecommerce.service.UserrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
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
    public CommonResult AddChart(@RequestParam String GoodId,
                              @RequestParam int number,
                              @RequestParam BigDecimal price,
                              @RequestParam String Attribute,
                              HttpServletRequest request)
    {

        return userrService.AddChart(GoodId, number, price, Attribute, request);
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

        return userrService.updateNumInChart(chartId,GoodId,Attribute,num);
    }

    @ApiOperation("删除购物车的商品")
    @RequestMapping(value = "/DeleteChartByGoodId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult DeleteChartByGoodId(@RequestParam String chartId)
    {

        return userrService.deleteChartByGoodId(chartId);
    }

    @ApiOperation("清空购物车的商品")
    @RequestMapping(value = "/ClearChart",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult ClearChart(@RequestParam String userId)
    {

        return userrService.clearChart(userId);
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
    public CommonResult GoodsReturnApply(@RequestParam String orderId,
                                         @RequestParam String message,
                                         HttpServletRequest request)
    {
        return userrService.GoodsReturnApply(orderId,message,request);
    }

    @ApiOperation("根据GoodId获取商品详情")
    @RequestMapping(value = "/GetGoodsDetailsByGoodId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetGoodsDetailsByGoodId(@RequestParam String GoodId)
    {
        GoodDetailParam goodDetailParam=userrService.getGoodsDetailsByGoodId(GoodId);
        return CommonResult.success(goodDetailParam);
    }

    @ApiOperation("根据GoodId获取商品详情")
    @RequestMapping(value = "/GetNeedCommentByUserId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getNeedCommentByUserId(@RequestParam int pageNum,
                                               @RequestParam int pageSize,
                                               HttpServletRequest request)
    {
        List<Order> list=userrService.getNeedCommentByUserId(pageNum, pageSize, request);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("根据GoodId获取商品详情")
    @RequestMapping(value = "/AddComment",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult AddComment(@RequestParam String GoodId,
                                   @RequestParam String message,
                                   HttpServletRequest request)
    {
        userrService.AddComment(GoodId, message, request);
        return CommonResult.success("评论成功");
    }

    @ApiOperation("根据用户Id获取用户信息")
    @RequestMapping(value = "/GetUserInformationByUserId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetUserInformationByUserId(@RequestParam String UserId,
                                                   HttpServletRequest request)
    {
        Userr user =userrService.getUserInformationByUserId(UserId,request);
        return CommonResult.success(user,"用户信息");
    }

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/ModifyUserInformation",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult ModifyUserInformation(@RequestParam String UserId,
                                              @RequestParam String password,
                                              @RequestParam String UserAddress,
                                              @RequestParam String telephone)
    {
        return userrService.modifyUserInformation(UserId, password,UserAddress,telephone);
    }

    @ApiOperation("用户删除评论")
    @RequestMapping(value = "/DeleteComment",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult DeleteComment(@RequestParam String OrderId,
                                      HttpServletRequest request)
    {
        return userrService.deleteComment(OrderId,request);
    }

    @ApiOperation("根据商家Id获取商家")
    @RequestMapping(value = "/GetShopByShopId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetShopByShopId(@RequestParam String ShopId)
    {
        Shop shop=userrService.getShopByShopId(ShopId);
        return CommonResult.success(shop);
    }

    @ApiOperation("确认订单")
    @RequestMapping(value = "/ConfirmOrderByChart",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult ConfirmOrderByChart(@RequestBody List<ChartsParam> chartsParam,
                                            BindingResult bindingResult)
    {
        return userrService.ConfirmOrderByChart(chartsParam);
    }

    @ApiOperation("获取商品的评论")
    @RequestMapping(value = "/GetCommentByGoodId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetCommentByGoodId(@RequestParam String GoodId,
                                           @RequestParam int pageNum,
                                           @RequestParam int pageSize)
    {
        List<CommentParam> list= userrService.getCommentByGoodId (GoodId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

}
