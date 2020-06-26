package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.common.utils.JwtTokenUtil;
import com.example.ecommerce.component.VerifyFailedSender;
import com.example.ecommerce.component.VerifySuccessSender;
import com.example.ecommerce.dao.AddSkuDao;
import com.example.ecommerce.dao.ManagerDao;
import com.example.ecommerce.dto.AdminUserDetails;
import com.example.ecommerce.mbg.mapper.*;
import com.example.ecommerce.mbg.model.*;
import com.example.ecommerce.service.ManagerService;
import com.example.ecommerce.service.ShopService;
import com.example.ecommerce.service.UserpermissionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    private ShopService shopService;

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
    private ManagerMapper managerMapper;

    @Autowired(required = false)
    private ManagerDao managerDao;

    @Autowired(required = false)
    private LoginrecordMapper loginrecordMapper;

    @Autowired(required = false)
    private UserrMapper userrMapper;

    @Autowired(required = false)
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired(required = false)
    private GoodSkuMapper goodSkuMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VerifySuccessSender verifySuccessSender;

    @Autowired
    private VerifyFailedSender verifyFailedSender;

    @Override
    public String login(String username, String password) {
        String token=null;
        try{
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(managerMapper.selectByPrimaryKey(username)!=null) {
                Manager m = managerMapper.selectByPrimaryKey(username);
                if (!passwordEncoder.matches(password, m.getAdminword())) {
                    throw new BadCredentialsException("密码不正确");
                }
            }else
            {
                throw new BadCredentialsException("用户名不存在");
            }

            if(!passwordEncoder.matches(password,userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }

            Loginrecord loginrecord = new Loginrecord();
            loginrecord.setLogintime(new Date());
            loginrecord.setRole(1);
            loginrecord.setUserid(username);
            loginrecordMapper.insert(loginrecord);

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
            managerService.sendEmail(ShopId,"商家注册审核通过");
            return CommonResult.success(ShopId,"商家注册审核通过，邮件发送成功");
        }
        if(num.equals("0"))
        {
            managerService.sendEmail(ShopId,"商家注册审核失败");
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
        VerifyGoodSendEmail(goods.getShopid(),"1");

        return CommonResult.success(Goodid,"商品上架成功");
    }

    @Override
    public CommonResult VerifyGoodFailed(String Goodid) {
        Goods good=goodsMapper.selectByPrimaryKey(Goodid);
        goodsMapper.deleteByPrimaryKey(Goodid);
        GoodSkuExample goodSkuExample = new GoodSkuExample();
        goodSkuExample.createCriteria().andGoodidEqualTo(Goodid);
        goodSkuMapper.deleteByExample(goodSkuExample);


        VerifyGoodSendEmail(good.getShopid(),"0");
        return CommonResult.success(Goodid,"商品审核失败，拒绝上架");
    }

    @Override
    public CommonResult VerifyGoodSendEmail(String Shopid, String num) {
        if(num.equals("1"))
        {
            sendEmail(Shopid,"商品上架成功了！");
            return CommonResult.success(Shopid,"商品上架成功，邮件发送成功");
        }
        if(num.equals("0"))
        {
            sendEmail(Shopid,"商品上架失败");
            return CommonResult.success(Shopid,"商品上架失败，已发邮件通知");
        }
        return null;
    }

    @Override
    public CommonResult sendEmail(String emailAddress, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1005131042@qq.com");
        mailMessage.setTo(emailAddress);
        mailMessage.setSubject(message+"通知");
        mailMessage.setSentDate(new Date());
        mailMessage.setText("用户名为："+emailAddress+"的账号  操作："+message);
        javaMailSender.send(mailMessage);

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

    @Override
    public CommonResult WithdrawGoodsByGoodId(String GoodId) {
        return shopService.WithdrawGoodsByGoodId(GoodId);
    }

    @Override
    public List<Goods> getAllDownGoods(int pageNum, int pageSize) {

        GoodsExample g= new GoodsExample();
        g.createCriteria().andCheckstateEqualTo(1).andUpdownstateGreaterThanOrEqualTo(2);

        PageHelper.startPage(pageNum, pageSize);

        return goodsMapper.selectByExampleWithBLOBs(g);
    }

    @Override
    public CommonResult UptheDownGoods(String GoodId, int num) {
        if(num==1)
        {
            Goods goods=goodsMapper.selectByPrimaryKey(GoodId);
            goods.setUpdownstate(1);

            goodsMapper.updateByPrimaryKeySelective(goods);

            return CommonResult.success("审核通过，商品二次上架");
        }
        if(num==2)
        {
            Goods goods=goodsMapper.selectByPrimaryKey(GoodId);
            goods.setUpdownstate(3);

            goodsMapper.updateByPrimaryKeySelective(goods);
            return CommonResult.failed("拒绝商品上架");
        }

        return CommonResult.failed("只能输入1或2哦");
    }

    @Override
    public List<Userr> getUserbeVIP(int pageNum, int pageSize) {
        List<Userr> list=managerDao.SelectUserBeVIP();
        PageHelper.startPage(pageNum, pageSize);

        return list;
    }

    @Override
    public CommonResult VerifyUserBeVIP(String UserId, int num) {
       if(num==1)
       {
           Userr userr = userrMapper.selectByPrimaryKey(UserId);
           userr.setUserpower(1);

           userrMapper.updateByPrimaryKey(userr);

           return CommonResult.success(userr.getUserid(),"用户升级为VIP");
       }
       if(num==0)
       {
           return CommonResult.failed("拒绝升级哦");
       }

       return CommonResult.failed("只能输入0或1哦");
    }

}
