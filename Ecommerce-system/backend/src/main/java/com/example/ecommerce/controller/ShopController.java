package com.example.ecommerce.controller;

import com.example.ecommerce.common.api.CommonPage;
import com.example.ecommerce.common.api.CommonResult;
import com.example.ecommerce.dao.ShopDao;
import com.example.ecommerce.dto.AddSkuParam;
import com.example.ecommerce.dto.GoodsParam;
import com.example.ecommerce.mbg.model.*;
import com.example.ecommerce.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.xml.transform.OutputKeys.ENCODING;

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

    @Autowired(required = false)
    private ShopDao shopDao;

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


    @ApiOperation("商家添加SKU")
    @RequestMapping(value = "/AddSkuByGoodId",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult AddSkuByGoodId(@RequestBody AddSkuParam addSkuParam,BindingResult bindingResult)
    {
        return shopService.AddSkuByGoodId(addSkuParam);
    }

    @ApiOperation("SKU增加库存")
    @RequestMapping(value = "/AddNumberInSku",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult AddNumberInSku(@RequestParam int skuId,
                                       @RequestParam int number)
    {
        return shopService.AddNumberInSku(skuId,number);
    }

    @ApiOperation("根据skuid删除sku")
    @RequestMapping(value = "/ModifySku",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult ModifySku(@RequestParam int skuId,
                                  @RequestParam String picture,
                                  @RequestParam BigDecimal price,
                                  @RequestParam BigDecimal vipprice)
    {
        return shopService.ModifySku(skuId,price, vipprice,picture);
    }

    @ApiOperation("根据商家id获取下架的商品")
    @RequestMapping(value = "/getDownGoodsByShopId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getDownGoodsByShopId(@RequestParam String ShopId,
                                       @RequestParam(value = "pageNum") @ApiParam("页码") int pageNum,
                                       @RequestParam(value = "pageSize") @ApiParam("页面大小") int pageSize)
    {
        List<Goods> list=shopService.getDownGoodsByShopId(ShopId,pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }


 /*   @ApiOperation("商家通过GoodID添加商品SKU")
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
    public CommonResult GetGoodByShopId(@RequestParam  String ShopId,
                                  @RequestParam int pageNum,
                                  @RequestParam int pageSize)
    {
         List<Goods> goods= shopService.GetGoodByShopId(ShopId,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(goods));
    }
*/

    @ApiOperation("商家申请二次上架")
    @RequestMapping(value = "/ApplyDownGoodsUp",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult ApplyDownGoodsUp(@RequestParam String GoodId)
    {
        return shopService.ApplyDownGoodsUp(GoodId);
    }


    @ApiOperation("查看自家商品的订单记录")
    @RequestMapping(value = "/ExportOrder",method = RequestMethod.GET)
    @ResponseBody
    public void ExportOrder(@RequestParam  String ShopId, HttpServletResponse response) throws IOException {
        List<List<String>> excelData = new ArrayList<>();

        List<String> head = new ArrayList<>();
        head.add("第一列");
        head.add("第二列");
        head.add("第三列");

        List<String> data1 = new ArrayList<>();
        data1.add("123");
        data1.add("234");
        data1.add("345");

        List<String> data2 = new ArrayList<>();
        data2.add("abc");
        data2.add("bcd");
        data2.add("cde");

        excelData.add(head);
        excelData.add(data1);
        excelData.add(data2);

        String sheetName = "测试";
        String fileName = "ExcelTest.csv";

        shopService.exportExcel(response, excelData, sheetName, fileName, 15);
    }


    @ApiOperation("导出订单记录")
    @RequestMapping(value = "/goodsExcel",method = RequestMethod.GET)
    @ResponseBody
    public void goodsExcel(HttpServletResponse response){
        XSSFWorkbook wb =shopService.showOrderExcelByShopId("123456");
        String filename = "Goods报表.xls";
        OutputStream outputStream =null;
        response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");

        try {
            filename = URLEncoder.encode(filename,"UTF-8");
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


    @ApiOperation("商家上架商品")
    @RequestMapping(value = "/DownGoodsByGoodId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult DownGoodsByGoodId(@RequestParam String GoodId,
                                          @RequestParam String ShopId)
    {
        return shopService.downGoodsByGoodId(GoodId, ShopId);
    }


}
