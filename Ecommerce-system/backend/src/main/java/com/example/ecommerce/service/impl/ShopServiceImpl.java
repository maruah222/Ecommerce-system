package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.common.utils.JwtTokenUtil;
import com.example.ecommerce.common.utils.TokenTranslate;
import com.example.ecommerce.common.utils.TranslateToToken;
import com.example.ecommerce.component.OverTimeCancelSender;
import com.example.ecommerce.component.ShopRegisterSender;
import com.example.ecommerce.dao.AddSkuDao;
import com.example.ecommerce.dao.ShopDao;
import com.example.ecommerce.dto.GoodsParam;
import com.example.ecommerce.mbg.mapper.*;
import com.example.ecommerce.mbg.model.*;
import com.example.ecommerce.service.ManagerService;
import com.example.ecommerce.service.ShopService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: rain
 * @date: 2020/6/4 21:48
 * @description:
 */
@Service
public class ShopServiceImpl implements ShopService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired(required = false)
    private ShopMapper shopMapper;

    @Autowired(required = false)
    private UserpermissionMapper userpermissionMapper;

    @Autowired(required = false)
    private GoodsMapper goodsMapper;

    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Autowired(required = false)
    private OrderReturnMapper orderReturnMapper;

    @Autowired(required = false)
    private GoodSkuMapper goodSkuMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private OverTimeCancelSender overTimeCancelSender;

    @Autowired(required = false)
    private AddSkuDao addSkuDao;

    @Autowired
    private TokenTranslate tokenTranslate;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired(required = false)
    private ShopDao shopDao;

    @Override
    public CommonResult SellerRegister(String ShopId, String password, String Shopname, String Sellername, String address, String sellertelephone) {
        ShopExample shopExample = new ShopExample();
        shopExample.createCriteria().andSellernameEqualTo(ShopId);
        List<Shop> shopList = shopMapper.selectByExample(shopExample);
        if (shopList.size() > 0) {
            return CommonResult.failed("用户名已经注册");
        }

        String p = passwordEncoder.encode(password);
        Shop shop = new Shop();
        shop.setSellername(Sellername);
        shop.setRegisterstate(0);
        shop.setSellerpassword(p);
        shop.setShopaddress(address);
        shop.setShopname(Shopname);
        shop.setTotalsales(0);
        shop.setShopid(ShopId);
        shop.setSellertelephone(sellertelephone);


        shopMapper.insert(shop);


        SendDelayMessageOverTime(ShopId);
        return CommonResult.success(Sellername, "注册请求已提交，等待审核");
    }

    @Override
    public String SellerLogin(String Sellername, String Sellerpassword) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(Sellername);

            if(shopMapper.selectByPrimaryKey(Sellername)!=null) {
                Shop shop = shopMapper.selectByPrimaryKey(Sellername);
                if (!passwordEncoder.matches(Sellerpassword, shop.getSellerpassword())) {
                    throw new BadCredentialsException("密码不正确");
                }
            }else
            {
                throw new BadCredentialsException("用户名不存在");
            }

            if (!passwordEncoder.matches(Sellerpassword, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token1);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
        }
        return token;
    }

    @Override
    public CommonResult ApplyGoodsUp(GoodsParam goodsParam) {
        if (goodsParam.getShopId().length() == 0) {
            return CommonResult.failed("商家ID不能为空啊");
        }

        Goods good = new Goods();
        good.setGoodid(goodsParam.getGoodId());
        good.setUpdownstate(0);
        good.setCheckstate(0);
        good.setAllsellnumber(0);
        good.setCategoryid(goodsParam.getCategoryId());
        good.setGoodpicture(goodsParam.getGoodpicture());
        good.setFrontpicture(goodsParam.getFrontpicture());
        good.setIntroduction(goodsParam.getIntroduction());
        good.setIspackage(goodsParam.getIsPackage());
        good.setShangtime(new Date());
        good.setShopid(goodsParam.getShopId());
        good.setGoodname(goodsParam.getGoodname());

        goodsMapper.insert(good);

        addSkuDao.InsertList(goodsParam.getSkus());
        return CommonResult.success(goodsParam.getGoodname(), "商品上架申请已经提交，请等待12h审核");
    }

    @Override
    public CommonResult WithdrawGoodsByGoodId(String GoodId) {
        Goods goods = goodsMapper.selectByPrimaryKey(GoodId);
        goods.setUpdownstate(2);

        goodsMapper.updateByPrimaryKeySelective(goods);
        return CommonResult.success(goods.getGoodname(),"下架成功");
    }

    @Override
    public void SendDelayMessageOverTime(String ShopId) {
        long delayTimes = 1000 * 60*60*12;
        overTimeCancelSender.sendMessage(ShopId, delayTimes);
    }

    @Override
    public CommonResult CancelRegister(String ShopId) {
        if (shopMapper.selectByPrimaryKey(ShopId) != null) {
            if (shopMapper.selectByPrimaryKey(ShopId).getRegisterstate() != 0) {
                return CommonResult.success("该商家的注册已被审核，可直接登陆");
            }
            shopMapper.deleteByPrimaryKey(ShopId);
            managerService.sendEmail(ShopId,"商家注册超过12小时未审核，自动取消");

            return CommonResult.success("成功删除");
        } else {
            return CommonResult.success("该账号不存在");
        }
    }

    @Override
    public List<OrderReturn> getNeedVerifyReturnByShopId(String ShopId,int pageNum, int pageSize) {
        OrderReturnExample orderExample = new OrderReturnExample();
        orderExample.createCriteria().andOrderstatusEqualTo(0).andShopidEqualTo(ShopId);

        return orderReturnMapper.selectByExampleWithBLOBs(orderExample);
    }

    @Override
    public CommonResult VerifyReturnSuccess(String OrderId) {
        Order order = orderMapper.selectByPrimaryKey(OrderId);
        order.setState(5);

        OrderReturnExample o=new OrderReturnExample();
        o.createCriteria().andOrderidEqualTo(order.getOrderid());

        List<OrderReturn> orderReturn = orderReturnMapper.selectByExampleWithBLOBs(o);
        orderReturn.get(0).setOrderstatus(1);
        orderReturnMapper.updateByPrimaryKey(orderReturn.get(0));

        VerifyReturnSendEmail(order.getUserid(), 1);
        return CommonResult.success("退货退款审核通过");
    }

    @Override
    public CommonResult VerifyReturnFailed(String OrderId) {
        Order order = orderMapper.selectByPrimaryKey(OrderId);
        order.setState(2);
        orderMapper.updateByPrimaryKey(order);


        OrderReturnExample o=new OrderReturnExample();
        o.createCriteria().andOrderidEqualTo(order.getOrderid());
        List<OrderReturn> orderReturn = orderReturnMapper.selectByExampleWithBLOBs(o);
        orderReturn.get(0).setOrderstatus(2);
        orderReturnMapper.updateByPrimaryKey(orderReturn.get(0));

        VerifyReturnSendEmail(order.getUserid(), 0);
        return CommonResult.success("拒绝退货退款，滚");
    }

    @Override
    public CommonResult VerifyReturnSendEmail(String userId, int num) {
        if (num == 1) {
            return CommonResult.success(userId, "退货退款审核通过，邮件发送成功");
        }
        if (num == 0) {
            return CommonResult.success(userId, "退货退款审核失败，已发邮件通知");
        }
        return null;
    }

    @Override
    public Shop getInformationByShopId(String ShopId) {

        return shopMapper.selectByPrimaryKey(ShopId);
    }

    @Override
    public CommonResult modifyShopInformation(String sellername, String password, String shopaddress, String shopname, String telephone,String shopId){
        Shop shop = new Shop();
        shop.setShopid(shopId);
        shop.setSellertelephone(telephone);
        shop.setShopname(shopname);
        shop.setShopaddress(shopaddress);
        shop.setSellername(sellername);

        shopMapper.updateByPrimaryKeySelective(shop);
        return CommonResult.success("修改成功");
    }



    @Override
    public CommonResult DeleteSkuBySkuId(int skuId) {
        goodSkuMapper.deleteByPrimaryKey(skuId);
        return CommonResult.success("sku删除成功");
    }



    @Override
    public XSSFWorkbook showOrderExcelByShopId(String ShopId) {
/*
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopidEqualTo(ShopId);

        List<Goods> list = goodsMapper.selectByExample(goodsExample);

        List<Order> orders = shopDao.selectOrderByGoodId(list);

        XSSFWorkbook x = new XSSFWorkbook();
        Sheet sheet = x.createSheet("该商品的订单记录");
        Row row = sheet.createRow(0);

        row.createCell(0).setCellValue("订单ID");
        row.createCell(1).setCellValue("用户ID");
        row.createCell(2).setCellValue("商品ID");
        row.createCell(3).setCellValue("订单状态");
        row.createCell(4).setCellValue("付款时间");
        row.createCell(5).setCellValue("评论内容");
        row.createCell(6).setCellValue("评论时间");
        row.createCell(7).setCellValue("数量");
        row.createCell(8).setCellValue("单价");
        row.createCell(9).setCellValue("属性");
        row.createCell(10).setCellValue("总价");

        int i=1;
        for(Order order:orders)
        {
        }*/
        return null;
    }

    @Override
    public List<GoodSku> getSkuByGoodId(String GoodId,int pageNum,int pageSize)
    {
        GoodSkuExample goodSkuExample = new GoodSkuExample();
        goodSkuExample.createCriteria().andGoodidEqualTo(GoodId);

        PageHelper.startPage(pageNum, pageSize);

        return goodSkuMapper.selectByExample(goodSkuExample);
    }

    @Override
    public CommonResult AddSkuByGoodId(String GoodId,int SkuId,int num,BigDecimal price, BigDecimal vipprice,int Left_number,String picture,String Attribute){

        GoodSkuExample g = new GoodSkuExample();
        g.createCriteria().andGoodidEqualTo(GoodId);
        List<GoodSku> list = goodSkuMapper.selectByExample(g);
        if(list==null) {

            return CommonResult.failed("这个商品不存在");
        }

        list.get(0).setGoodid(GoodId);
        list.get(0).setSkuid(SkuId);
        list.get(0).setPrice(price);
        list.get(0).setGoodid(GoodId);
        list.get(0).setVipprice(vipprice);
        list.get(0).setLeftNumber(Left_number);
        list.get(0).setPicture(picture);
        list.get(0).setAttribute(Attribute);
        goodSkuMapper.insert(list.get(0));

        return CommonResult.success("添加成功");



    }
    @Override
    public CommonResult AddNumberInSku(int skuid,int num)
    {
        GoodSkuExample g = new GoodSkuExample();
        g.createCriteria().andSkuidEqualTo(skuid);
        List<GoodSku> list = goodSkuMapper.selectByExample(g);
        int Left_Number = list.get(0).getLeftNumber();
        if(Left_Number<num)
        {
            return CommonResult.failed("库存数量必须增加");
        }

        GoodSku goodSku =goodSkuMapper.selectByPrimaryKey(skuid);
        goodSku.setNumber(num);
        goodSkuMapper.updateByPrimaryKeySelective(goodSku);
        return CommonResult.success("库存数量增加成功");

    }

    @Override
    public CommonResult ModifySku(int SKUId,String GoodId, BigDecimal price, BigDecimal vipprice){
        GoodSkuExample g = new GoodSkuExample();
        g.createCriteria().andGoodidEqualTo(GoodId).andSkuidEqualTo(SKUId);
        List<GoodSku> list = goodSkuMapper.selectByExample(g);
        if(list==null) {

            return CommonResult.failed("这个商品不存在");
        }

//        String userId = tokenTranslate.GetUsernameByHeader(request);
//        String ChartId = managerService.getRandomCode();
//        Goods good =goodsMapper.selectByPrimaryKey(GoodId);
        GoodSku goodSku =goodSkuMapper.selectByPrimaryKey(SKUId);
        goodSku.setGoodid(GoodId);
        goodSku.setPrice(price);
        goodSku.setVipprice(vipprice);

        return CommonResult.success("添加成功");
    }


    @Override
    public List<Goods> GetGoodByShopId(String ShopId,int pageNum, int pageSize ){

        PageHelper.startPage(pageNum, pageSize);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopidEqualTo(ShopId);
        return goodsMapper.selectByExample(goodsExample);
    }
}

