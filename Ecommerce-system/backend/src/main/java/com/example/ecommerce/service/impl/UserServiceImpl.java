package com.example.ecommerce.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.common.utils.JwtTokenUtil;
import com.example.ecommerce.common.utils.TokenTranslate;
import com.example.ecommerce.component.CancelOrderSender;
import com.example.ecommerce.dao.UserDao;
import com.example.ecommerce.dto.GoodDetailParam;
import com.example.ecommerce.mbg.mapper.*;
import com.example.ecommerce.mbg.model.*;
import com.example.ecommerce.service.ManagerService;
import com.example.ecommerce.service.UserrService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springfox.documentation.spi.service.contexts.SecurityContext;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author: rain
 * @date: 2020/6/1 11:47
 * @description:
 */
@Service
public class UserServiceImpl implements UserrService {

    @Autowired(required = false)
    private UserrMapper userrMapper;

    @Autowired(required = false)
    private UserpermissionMapper userpermissionMapper;

    @Autowired(required = false)
    private ChartMapper chartMapper;

    @Autowired(required = false)
    private GoodSkuMapper goodSkuMapper;

    @Autowired(required = false)
    private GoodsMapper goodsMapper;

    @Autowired(required = false)
    private ShopMapper shopMapper;

    @Autowired(required = false)
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Autowired(required = false)
    private OrderReturnMapper orderReturnMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ManagerService managerService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TokenTranslate tokenTranslate;

    @Autowired(required = false)
    private UserDao userDao;

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult register(String userid, String password,String telephone) {


        String text = "未评论";
        Userr userr = new Userr();
        userr.setUserid(userid);
        userr.setUseraddress(text);
        userr.setUsertelephone(telephone);

        UserrExample userrExample = new UserrExample();
        userrExample.createCriteria().andUseridEqualTo(userr.getUserid());
        List<Userr> userrList = userrMapper.selectByExample(userrExample);
        if(userrList.size()>0)
        {
            return CommonResult.failed("该用户名已注册");
        }

        String passw = passwordEncoder.encode(password);
        userr.setUserpassword(passw);

        String value="普通用户";
        Userpermission userpermission = new Userpermission();
        userpermission.setValue(value);
        userpermission.setName(userid);
        userpermission.setCreatetime(new Date());
        userpermission.setRole(0);

        userrMapper.insert(userr);
        userpermissionMapper.insert(userpermission);
        return CommonResult.success(userid, "注册成功");
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
        }
        return token;
    }

    @Override
    public CommonResult AddChart(String GoodId, int number, BigDecimal price, String Attribute,HttpServletRequest request) {

        GoodSkuExample g = new GoodSkuExample();
        g.createCriteria().andGoodidEqualTo(GoodId).andAttributeEqualTo(Attribute);
        List<GoodSku> list = goodSkuMapper.selectByExample(g);
        if(list==null) {
            if (list.get(0).getLeftNumber() == 0) {
                return CommonResult.failed("该商品的库存已空");
            }
            return CommonResult.failed("这个商品不存在");
        }

        String userId = tokenTranslate.GetUsernameByHeader(request);
        String ChartId = managerService.getRandomCode();
        Goods good =goodsMapper.selectByPrimaryKey(GoodId);

        Chart chart = new Chart();
        chart.setChartid(ChartId);
        chart.setCheckstate(0);
        chart.setGoodid(GoodId);
        chart.setNumber(number);
        chart.setPrice(price);
        chart.setAttribute(Attribute);
        chart.setUserid(userId);
        chart.setGoodname(good.getGoodname());

        chartMapper.insert(chart);

        list.get(0).setLeftNumber(list.get(0).getLeftNumber()-1);
        goodSkuMapper.updateByPrimaryKey(list.get(0));

        return CommonResult.success("添加成功");
    }

    @Override
    public List<Chart> getAllChartByUserId(int pageNum,int pageSize,HttpServletRequest request) {
        String userId = tokenTranslate.GetUsernameByHeader(request);
        if(userId.length()==0)
        {
            return new ArrayList<>();
        }
        ChartExample chartExample = new ChartExample();
        chartExample.createCriteria().andUseridEqualTo(userId);
        PageHelper.startPage(pageNum, pageSize);
        return chartMapper.selectByExample(chartExample);
    }

    @Override
    public CommonResult updateNumInChart(String chartId,String GoodId, String Attribute,int num) {
        GoodSkuExample g = new GoodSkuExample();
        g.createCriteria().andGoodidEqualTo(GoodId).andAttributeEqualTo(Attribute);
        List<GoodSku> list = goodSkuMapper.selectByExample(g);
        if(list.get(0).getLeftNumber()==0)
        {
            return CommonResult.failed("该商品的库存已空");
        }

        Chart chart =chartMapper.selectByPrimaryKey(chartId);
        chart.setNumber(num);

        chartMapper.updateByPrimaryKeySelective(chart);

        return CommonResult.success("购物车数量修改成功");
    }

    @Override
    public CommonResult deleteChartByGoodId(String chartId) {
            chartMapper.deleteByPrimaryKey(chartId);
            return CommonResult.success("删除成功");
    }

    @Override
    public CommonResult clearChart(String userId) {
        ChartExample chartExample = new ChartExample();
        chartExample.createCriteria().andUseridEqualTo(userId);

        chartMapper.deleteByExample(chartExample);

        return CommonResult.success("清空购物车成功");
    }

    @Override
    public List<Goods> getAllGoods(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return goodsMapper.selectByExample(new GoodsExample());
    }

    @Override
    public List<Goods> getGoodsByShopId(String shopid,int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopidEqualTo(shopid);
        return goodsMapper.selectByExample(goodsExample);
    }

    @Override
    public List<GoodsCategory> getAllGoodsCategory(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        return goodsCategoryMapper.selectByExample(new GoodsCategoryExample());
    }

    @Override
    public List<Shop> getAllShop(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        return shopMapper.selectByExample(new ShopExample());
    }

    @Override
    public Shop getShopByShopId(String ShopId) {
        return null;
    }

    @Override
    public List<Order> getAllOrderByUserId(int pageNum, int pageSize, HttpServletRequest request) {
        String userId = tokenTranslate.GetUsernameByHeader(request);
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andUseridEqualTo(userId);

        return orderMapper.selectByExampleWithBLOBs(orderExample);
    }


    @Override
    public Order getOrderDetail(String orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public CommonResult GetGoodsSuccess(String OrderId) {
        Order order = orderMapper.selectByPrimaryKey(OrderId);
        order.setGettime(new Date());
        order.setState(2);

        orderMapper.updateByPrimaryKey(order);
        return CommonResult.success(OrderId,"确认收货成功");
    }


    @Override
    public CommonResult GoodsReturnApply(String OrderId, String reason,HttpServletRequest request) {
        String userId = tokenTranslate.GetUsernameByHeader(request);
        Order order = orderMapper.selectByPrimaryKey(OrderId);
        order.setState(3);

        OrderReturn orderReturn = new OrderReturn();
        orderReturn.setMoney(order.getMoney());
        orderReturn.setOrderid(order.getOrderid());
        orderReturn.setOrderstatus(0);
        orderReturn.setReturnreason(reason);
        orderReturn.setGoodid(order.getGoodid());
        orderReturn.setMoney(order.getMoney());

        orderMapper.updateByPrimaryKey(order);
        orderReturnMapper.insert(orderReturn);
        return CommonResult.success("退款退货申请已提交，请等待审核");
    }

    @Override
    public CommonResult ConfirmOrderByChart(List<Chart> charts, HttpServletRequest request) {
        String userId = tokenTranslate.GetUsernameByHeader(request);
        List<Order> orders = new ArrayList<>();
        for(Chart chart:charts)
        {
            BigDecimal number = new BigDecimal(chart.getNumber().toString());
            BigDecimal money = chart.getPrice().multiply(number);
            Order order = new Order();
            order.setState(1);
            order.setComment("未评论");
            order.setPaytime(new Date());
            order.setUserid(userId);
            order.setNumber(chart.getNumber());
            order.setGoodid(chart.getGoodid());
            order.setPrice(chart.getPrice());
            order.setMoney(money);

            orders.add(order);
        }

        userDao.InsertOrder(orders);
        for(Order o:orders) {
            senDelayMessageCancelOrder(new String[]{o.getOrderid(),userId});
        }
        return CommonResult.success("提交订单成功，请30min内付款");
    }

    private void senDelayMessageCancelOrder(String[] orderId_userId) {
        long delayTimes = 60 * 30 * 1000;
        cancelOrderSender.sendMessage(orderId_userId,delayTimes);
    }
    @Override
    public CommonResult cancelOverTimeOrder(String[] orderId_userId) {
        if(orderMapper.selectByPrimaryKey(orderId_userId[0])!=null) {
            if (orderMapper.selectByPrimaryKey(orderId_userId[0]).getState() == 5) {
                return CommonResult.success("该订单已经被取消");
            }
            orderMapper.deleteByPrimaryKey(orderId_userId[0]);
            sendOrderCancelEmail(orderId_userId[0], orderId_userId[1]);
            return CommonResult.success("删除成功");
        }else {
            return CommonResult.failed("该订单已经被取消");
        }
    }

    @Override
    public CommonResult sendOrderCancelEmail(String orderId, String userId) {
        return CommonResult.success("邮件发送成功");
    }


    @Override
    public GoodDetailParam getGoodsDetailsByGoodId(String GoodId) {
        Goods goods = goodsMapper.selectByPrimaryKey(GoodId);
        String shopId = goods.getShopid();

        Shop shop = shopMapper.selectByPrimaryKey(shopId);

        GoodSkuExample goodSkuExample = new GoodSkuExample();
        goodSkuExample.createCriteria().andGoodidEqualTo(GoodId);
        List<GoodSku> goodSkus = goodSkuMapper.selectByExample(goodSkuExample);

        GoodDetailParam goodDetailParam = new GoodDetailParam();
        goodDetailParam.setGoodId(GoodId);
        goodDetailParam.setCategoryId(goods.getCategoryid());
        goodDetailParam.setFrontpicture(goods.getFrontpicture());
        goodDetailParam.setGoodname(goods.getGoodname());
        goodDetailParam.setGoodpicture(goods.getGoodpicture());
        goodDetailParam.setIntroduction(goods.getIntroduction());
        goodDetailParam.setIsPackage(goods.getIspackage());
        goodDetailParam.setIntroduction(goods.getIntroduction());
        goodDetailParam.setShopAddress(shop.getShopaddress());
        goodDetailParam.setSkus(goodSkus);

        return goodDetailParam;
    }

    @Override
    public CommonResult AddComment(String orderId, String message, HttpServletRequest request) {

        String userId = tokenTranslate.GetUsernameByHeader(request);
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andUseridEqualTo(userId).andStateEqualTo(2);

        List<Order> order=orderMapper.selectByExample(orderExample);
        order.get(0).setComment(message);
        order.get(0).setCommenttime(new Date());

        orderMapper.updateByPrimaryKeyWithBLOBs(order.get(0));

        return CommonResult.success("评论添加成功");
    }

    @Override
    public List<Order> getNeedCommentByUserId(int pageNum, int pageSize, HttpServletRequest request) {
        String userId = tokenTranslate.GetUsernameByHeader(request);
        PageHelper.startPage(pageNum, pageSize);
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andUseridEqualTo(userId).andStateEqualTo(2);

        return orderMapper.selectByExampleWithBLOBs(orderExample);
    }

    @Override
    public Userr getUserInformationByUserId(String UserId) {
        return null;
    }

    @Override
    public CommonResult modifyUserInformation(String UserId) {
        return null;
    }


}
