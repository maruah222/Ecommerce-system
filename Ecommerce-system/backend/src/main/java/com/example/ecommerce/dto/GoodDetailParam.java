package com.example.ecommerce.dto;

import com.example.ecommerce.mbg.model.GoodSku;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: rain
 * @date: 2020/6/20 12:19
 * @description:
 */
public class GoodDetailParam {

    @ApiModelProperty("商家Id")
    private String ShopId;

    @ApiModelProperty("商品Id")
    private String GoodId;

    @ApiModelProperty("商品名字")
    private String Goodname;

    @ApiModelProperty("商品图片")
    private String Goodpicture;

    @ApiModelProperty("商品介绍")
    private String introduction;


    @ApiModelProperty("是否包邮")
    private int isPackage;

    @ApiModelProperty("首页展示的图片")
    private String Frontpicture;

    @ApiModelProperty("分类Id")
    private String categoryId;

    @ApiModelProperty("商家地址")
    private String ShopAddress;

    @ApiModelProperty("SKU的信息")
    private List<GoodSku> skus;

    @ApiModelProperty("商家的名字")
    private String Shopname;


    public void setShopAddress(String shopAddress) {
        ShopAddress = shopAddress;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }

    public void setGoodId(String goodId) {
        GoodId = goodId;
    }

    public void setIsPackage(int isPackage) {
        this.isPackage = isPackage;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setGoodpicture(String goodpicture) {
        Goodpicture = goodpicture;
    }

    public void setGoodname(String goodname) {
        Goodname = goodname;
    }

    public void setFrontpicture(String frontpicture) {
        Frontpicture = frontpicture;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    public void setSkus(List<GoodSku> skus) {
        this.skus = skus;
    }


    public String getShopId() {
        return ShopId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getGoodpicture() {
        return Goodpicture;
    }

    public String getGoodname() {
        return Goodname;
    }

    public String getFrontpicture() {
        return Frontpicture;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public int getIsPackage() {
        return isPackage;
    }

    public List<GoodSku> getSkus() {
        return skus;
    }

    public String getGoodId() {
        return GoodId;
    }

    public String getShopAddress() {
        return ShopAddress;
    }

    public String getShopname() {
        return Shopname;
    }

    public void setShopname(String shopname) {
        Shopname = shopname;
    }
}
