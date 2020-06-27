package com.example.ecommerce.controller;

import com.example.ecommerce.common.api.CommonPage;
import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.dto.LoginParam;
import com.example.ecommerce.mbg.model.Goods;
import com.example.ecommerce.mbg.model.Order;
import com.example.ecommerce.mbg.model.Shop;
import com.example.ecommerce.mbg.model.Userr;
import com.example.ecommerce.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: rain
 * @date: 2020/6/3 23:54
 * @description:
 */
@Api(tags = "ManagerController",description = "管理员的操作接口")
@Controller
@RequestMapping("/Manager")
public class ManageController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private ManagerService managerService;

    @ApiOperation("管理员登录")
    @RequestMapping(value = "/Managerlogin",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody LoginParam loginParam,
                              BindingResult bindingResult)
    {
        String token = managerService.login(loginParam.getUsername(),loginParam.getPassword());
        if(token == null)
        {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("tokenhead",tokenHead);

        return CommonResult.success(map);
    }

    @ApiOperation("查看需要审核的商家注册")
    @RequestMapping(value = "/getNeedVerifyRegister",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getNeedVerifyRegister (@RequestParam(value = "pageNum") @ApiParam("页码") int pageNum,
                                               @RequestParam(value = "pageSize") @ApiParam("页面大小") int pageSize)
    {
        List<Shop> list = managerService.getNeedVerifyRegister(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("审核商家注册")
    @RequestMapping(value = "/VerifyShopRegister",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult VerifyShopRegister(@RequestParam String username,@RequestParam int value)
    {
        if(value==1)
        {
            return managerService.VerifyShopRegisterSuccess(username);
        }
        else if(value==0)
        {
            return managerService.VerifyShopRegisterFailed(username);
        }
        else
        {
            return CommonResult.failed("审核啊，你在干嘛？？");
        }
    }

    @ApiOperation("查看需要审核的上架商品")
    @RequestMapping(value = "/getNeedVerifyGoods",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getNeedVerifyGoods(@RequestParam(value = "pageNum") @ApiParam("页码") int pageNum,
                                           @RequestParam(value = "pageSize") @ApiParam("页面大小") int pageSize)
    {
        List<Goods> list=managerService.getNeedVerifyGoods(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("审核商品的上架")
    @RequestMapping(value = "/VerifyGoodsOn",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult VerifyGoodsOn(@RequestParam String username,@RequestParam int value)
    {
        if(value==1)
        {
            return managerService.VerifyGoodSuccess(username);
        }
        else if(value==0)
        {
            return managerService.VerifyGoodFailed(username);
        }
        else
        {
            return CommonResult.failed("审核啊，你在干嘛？？");
        }
    }

    @ApiOperation("h获取所有订单")
    @RequestMapping(value = "/getAllOrder",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAllOrder(@RequestParam(value = "pageNum") @ApiParam("页码") int pageNum,
                                    @RequestParam(value = "pageSize") @ApiParam("页面大小") int pageSize)
    {
        List<Order> list=managerService.getAllOrder(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("下架商品")
    @RequestMapping(value = "/WithdrawGoodsByGoodId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult WithdrawGoodsByGoodId(@RequestParam String GoodId)
    {
        return managerService.WithdrawGoodsByGoodId(GoodId);
    }


    @ApiOperation("获取所有被下架商品")
    @RequestMapping(value = "/GetAllDownGoods",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetAllDownGoods(@RequestParam(value = "pageNum") @ApiParam("页码") int pageNum,
                                        @RequestParam(value = "pageSize") @ApiParam("页面大小") int pageSize)
    {
        List<Goods> list=managerService.getAllDownGoods(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("审核二次上架的申请")
    @RequestMapping(value = "/UptheDownGoods",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult UptheDownGoods(@RequestParam String GoodId,
                                       @RequestParam int num)
    {
        return managerService.UptheDownGoods(GoodId,num);
    }

    @ApiOperation("获取消费3000以上的用户，可升级为vip")
    @RequestMapping(value = "/GetUserbeVIP",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult GetUserbeVIP(@RequestParam(value = "pageNum") @ApiParam("页码") int pageNum,
                                        @RequestParam(value = "pageSize") @ApiParam("页面大小") int pageSize)
    {
        List<Userr> list=managerService.getUserbeVIP(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }


    @ApiOperation("审核用户成为VIP，1是通过，0是拒绝")
    @RequestMapping(value = "/VerifyUserBeVIP",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult VerifyUserBeVIP(@RequestParam String UserId,
                                       @RequestParam int num)
    {
        return managerService.VerifyUserBeVIP(UserId,num);
    }


    @ApiOperation("导出登录记录")
    @RequestMapping(value = "/ShowLoginRecordinExcel",method = RequestMethod.GET)
    @ResponseBody
    public void ShowLoginRecordinExcel(HttpServletResponse response) {
        XSSFWorkbook wb = managerService.showLoginRecordinExcel();
        String filename = "Loginrecord.xls";
        OutputStream outputStream = null;
        response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");

        try {
            filename = URLEncoder.encode(filename, "UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(filename));
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("导出商品上架申请记录")
    @RequestMapping(value = "/ShowGoodUpRecordinExcel",method = RequestMethod.GET)
    @ResponseBody
    public void ShowGoodUpRecordinExcel(HttpServletResponse response) {
        XSSFWorkbook wb = managerService.showGoodUpRecordinExcel();
        String filename = "GoodsUpRecord.xls";
        OutputStream outputStream = null;
        response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");

        try {
            filename = URLEncoder.encode(filename, "UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(filename));
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
