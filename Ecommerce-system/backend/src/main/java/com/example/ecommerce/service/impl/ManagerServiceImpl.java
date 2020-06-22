package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.common.utils.JwtTokenUtil;
import com.example.ecommerce.component.VerifyFailedSender;
import com.example.ecommerce.component.VerifySuccessSender;
import com.example.ecommerce.dao.AddSkuDao;
import com.example.ecommerce.dto.AdminUserDetails;
import com.example.ecommerce.mbg.mapper.*;
import com.example.ecommerce.mbg.model.*;
import com.example.ecommerce.service.ManagerService;
import com.example.ecommerce.service.UserpermissionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author: rain
 * @date: 2020/6/2 17:48
 * @description:
 */
@Service
public class ManagerServiceImpl implements ManagerService {


    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserpermissionService userpermissionService;

    @Autowired(required = false)
    private UserpermissionMapper userpermissionMapper;

    @Autowired(required = false)
    private GoodsMapper goodsMapper;

    @Autowired(required = false)
    private ShopMapper shopMapper;

    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Autowired(required = false)
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired(required = false)
    private GoodSkuMapper goodSkuMapper;

    @Autowired
    private VerifySuccessSender verifySuccessSender;

    @Autowired
    private VerifyFailedSender verifyFailedSender;

    @Override
    public String login(String username, String password) {
        String token=null;
        try{
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token1);
            token=jwtTokenUtil.generateToken(userDetails);
        }catch (AuthenticationException e){
        }
        return token;
    }

    @Override
    public List<Shop> getNeedVerifyRegister(int pageNum, int pageSize) {
        ShopExample shopExample = new ShopExample();
        shopExample.createCriteria().andRegisterstateEqualTo(0);
        PageHelper.startPage(pageNum, pageSize);
        return shopMapper.selectByExample(shopExample);
    }

    @Override
    public CommonResult VerifyShopRegisterSuccess(String ShopId) {

        Shop shop = shopMapper.selectByPrimaryKey(ShopId);
        shop.setRegisterstate(1);
        String value="商家";
        Userpermission userpermission = new Userpermission();
        userpermission.setValue(value);
        userpermission.setName(ShopId);
        userpermission.setCreatetime(new Date());
        userpermission.setRole(2);

        userpermissionMapper.insert(userpermission);
        shopMapper.updateByPrimaryKey(shop);

        VerifyRegisterSendEmail(shop.getShopid(),"1");
        return CommonResult.success(ShopId,"商家注册审核成功");
    }

    @Override
    public CommonResult VerifyShopRegisterFailed(String ShopId) {
        shopMapper.deleteByPrimaryKey(ShopId);

        VerifyRegisterSendEmail(ShopId,"0");
        return CommonResult.success(ShopId,"该商家审核不通过");
    }

    @Override
    public CommonResult VerifyRegisterSendEmail(String ShopId, String num) {
        if(num.equals("1"))
        {
            return CommonResult.success(ShopId,"商家注册审核通过，邮件发送成功");
        }
        if(num.equals("0"))
        {
            return CommonResult.success(ShopId,"商家注册审核失败，已发邮件通知");
        }
        return null;
    }

    @Override
    public List<Goods> getNeedVerifyGoods(int pageNum, int pageSize) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andCheckstateEqualTo(0);
        return goodsMapper.selectByExampleWithBLOBs(goodsExample);
    }

    @Override
    public CommonResult VerifyGoodSuccess(String Goodid) {
        Goods goods = goodsMapper.selectByPrimaryKey(Goodid);
        goods.setCheckstate(1);
        goods.setUpdownstate(1);
        goodsMapper.updateByPrimaryKey(goods);
        VerifyGoodSendEmail(Goodid,"1");

        return CommonResult.success(Goodid,"商品上架成功");
    }

    @Override
    public CommonResult VerifyGoodFailed(String Goodid) {
        goodsMapper.deleteByPrimaryKey(Goodid);
        GoodSkuExample goodSkuExample = new GoodSkuExample();
        goodSkuExample.createCriteria().andGoodidEqualTo(Goodid);
        goodSkuMapper.deleteByExample(goodSkuExample);

        VerifyGoodSendEmail(Goodid,"0");
        return CommonResult.success(Goodid,"商品审核失败，拒绝上架");
    }

    @Override
    public CommonResult VerifyGoodSendEmail(String Goodid, String num) {
        if(num.equals("1"))
        {

            return CommonResult.success(Goodid,"商品上架成功，邮件发送成功");
        }
        if(num.equals("0"))
        {
            return CommonResult.success(Goodid,"商品上架失败，已发邮件通知");
        }
        return null;
    }

    @Override
    public CommonResult sendEmail(String emailAddress, String message) {
        return CommonResult.success("账号："+emailAddress,"成功发送邮件");
    }

    @Override
    public String getRandomCode() {
        StringBuilder s=new StringBuilder();
        Random random = new Random();
        for(int i=0;i<6;i++)
        {
            s.append(random.nextInt(10));
        }
        return s.toString();
    }

    @Override
    public List<Order> getAllOrder(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        return orderMapper.selectByExampleWithBLOBs(new OrderExample());
    }

}
