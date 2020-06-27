package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.common.utils.JwtTokenUtil;
import com.example.ecommerce.common.utils.TokenTranslate;
import com.example.ecommerce.common.utils.TranslateToToken;
import com.example.ecommerce.component.OverTimeCancelSender;
import com.example.ecommerce.component.ShopRegisterSender;
import com.example.ecommerce.dao.AddSkuDao;
import com.example.ecommerce.dao.ShopDao;
import com.example.ecommerce.dto.AddSkuParam;
import com.example.ecommerce.dto.GoodsParam;
import com.example.ecommerce.mbg.mapper.*;
import com.example.ecommerce.mbg.model.*;
import com.example.ecommerce.service.ManagerService;
import com.example.ecommerce.service.ShopService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.RichTextString;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private LoginrecordMapper loginrecordMapper;

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

            Loginrecord loginrecord = new Loginrecord();
            loginrecord.setLogintime(new Date());
            loginrecord.setRole(2);
            loginrecord.setUserid(Sellername);
            loginrecordMapper.insert(loginrecord);

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

        orderMapper.updateByPrimaryKey(order);

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
            managerService.sendEmail(userId,"退货退款申请通过");
            return CommonResult.success(userId, "退货退款审核通过，邮件发送成功");
        }
        if (num == 0) {
            managerService.sendEmail(userId,"拒绝退货退款");
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
    public CommonResult AddSkuByGoodId(AddSkuParam addSkuParam) {
        GoodSku goodSku = new GoodSku();
        goodSku.setNumber(addSkuParam.getNumber());
        goodSku.setLeftNumber(addSkuParam.getLeftNumber());
        goodSku.setVipprice(addSkuParam.getVipprice());
        goodSku.setPrice(addSkuParam.getPrice());
        goodSku.setGoodid(addSkuParam.getGoodid());
        goodSku.setAttribute(addSkuParam.getAttribute());
        goodSku.setPicture(addSkuParam.getPicture());

        goodSkuMapper.insert(goodSku);
        return CommonResult.success("添加SKu成功");
    }

    @Override
    public CommonResult AddNumberInSku(int skuId,int number) {
        GoodSku goodSku = goodSkuMapper.selectByPrimaryKey(skuId);

        int num=number-goodSku.getNumber();
        goodSku.setNumber(number);
        goodSku.setLeftNumber(goodSku.getLeftNumber()+num);
        goodSkuMapper.updateByPrimaryKeySelective(goodSku);
        return CommonResult.success(skuId,"修改成功");
    }

    @Override
    public CommonResult ModifySku(int skuId,BigDecimal price, BigDecimal vipprice,String picture) {
        GoodSku goodSku = goodSkuMapper.selectByPrimaryKey(skuId);

        goodSku.setSkuid(skuId);
        goodSku.setPicture(picture);
        goodSku.setPrice(price);
        goodSku.setVipprice(vipprice);

        goodSkuMapper.updateByPrimaryKeySelective(goodSku);

        return CommonResult.success(skuId,"修改成功");
    }

    @Override
    public List<Goods> getDownGoodsByShopId(String ShopId, int pageNum, int pageSize) {
        GoodsExample g= new GoodsExample();
        g.createCriteria().andShopidEqualTo(ShopId).andUpdownstateEqualTo(2).andCheckstateEqualTo(1);

        List<Goods> list = goodsMapper.selectByExample(g);

        return list;
    }

    @Override
    public CommonResult downGoodsByGoodId(String GoodId,String ShopId) {
        GoodsExample goodsExample= new GoodsExample();
        goodsExample.createCriteria().andShopidEqualTo(ShopId).andShopidEqualTo(ShopId);
        Goods goods = goodsMapper.selectByPrimaryKey(GoodId);

        goods.setUpdownstate(2);

        goodsMapper.updateByPrimaryKeySelective(goods);

        return CommonResult.success("下架成功");
    }

    @Override
    public CommonResult ApplyDownGoodsUp(String GoodId) {
        Goods goods=goodsMapper.selectByPrimaryKey(GoodId);
        goods.setUpdownstate(0);

        return CommonResult.success("二次上架的申请已提交，请等待审核");
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
    public XSSFWorkbook showOrderExcelByShopId(String ShopId) {
         GoodsExample goodsExample = new GoodsExample();
         goodsExample.createCriteria().andShopidEqualTo(ShopId);

         List<Goods> list = goodsMapper.selectByExample(goodsExample);

         List<Order> orders = shopDao.selectOrderByGoodId(list);

         XSSFWorkbook x = new XSSFWorkbook();
         Sheet sheet = x.createSheet(ShopId+"商品的订单记录");
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
         for(Order o:orders)
         {
             Row r= sheet.createRow(i);

             r.createCell(0).setCellValue(o.getOrderid());
             r.createCell(1).setCellValue(o.getUserid());
             r.createCell(2).setCellValue(o.getGoodid());
             r.createCell(3).setCellValue(o.getState());
             r.createCell(4).setCellValue(o.getPaytime());
             r.createCell(5).setCellValue(o.getComment());
             r.createCell(6).setCellValue(o.getCommenttime());
             r.createCell(7).setCellValue(o.getNumber());
             r.createCell(8).setCellValue(o.getPrice().toString());
             r.createCell(9).setCellValue(o.getAttribute());
             r.createCell(10).setCellValue(o.getMoney().toString());
         }

        return x;
    }

    @Override
    public List<Order> getOrdernotGet(String ShopId,int pageNum, int pageSize) {

        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andShopidEqualTo(ShopId);

        List<Goods> goods = goodsMapper.selectByExampleWithBLOBs(goodsExample);

        List<Order> list = shopDao.selectnotGetOrderByGoodId(goods);

        PageHelper.startPage(pageNum, pageSize);

        return list;
    }
}

