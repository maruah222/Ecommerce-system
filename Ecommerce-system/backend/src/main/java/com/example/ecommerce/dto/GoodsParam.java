package com.example.ecommerce.dto;

import com.example.ecommerce.mbg.model.GoodSku;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rain
 * @date: 2020/6/15 15:35
 * @description:
 */
//上架商品时的数据
public class GoodsParam {

    @ApiModelProperty("商家Id")
    private String ShopId;

    @ApiModelProperty("商家名字")
    private String Shopname;

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

    @ApiModelProperty("SKU的信息")
    private List<GoodSku> skus;

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
}
