package com.example.ecommerce.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: rain
 * @date: 2020/6/26 10:11
 * @description:
 */
public class GoodsPriceParam {
    @ApiModelProperty(value = "商品ID")
    private String goodid;

    @ApiModelProperty(value = "商家ID")
    private String shopid;

    @ApiModelProperty(value = "商品名字")
    private String goodname;

    @ApiModelProperty(value = "商品图片")
    private String goodpicture;

    @ApiModelProperty(value = "审核状态，0为待审核上架，1为已审核成功上架")
    private Integer checkstate;

    @ApiModelProperty(value = "包邮状态，0为不包邮，1为包邮")
    private Integer ispackage;

    @ApiModelProperty(value = "首页图片")
    private String frontpicture;

    @ApiModelProperty(value = "上架时间")
    private Date shangtime;

    @ApiModelProperty(value = "分类Id")
    private String categoryid;

    @ApiModelProperty(value = "总销售量")
    private Integer allsellnumber;

    @ApiModelProperty(value = "上下架状态，0是待上架，1是已上架，2是已下架")
    private Integer updownstate;

    @ApiModelProperty(value = "商品介绍")
    private String introduction;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    private static final long serialVersionUID = 1L;

    public String getGoodid() {
        return goodid;
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getGoodpicture() {
        return goodpicture;
    }

    public void setGoodpicture(String goodpicture) {
        this.goodpicture = goodpicture;
    }

    public Integer getCheckstate() {
        return checkstate;
    }

    public void setCheckstate(Integer checkstate) {
        this.checkstate = checkstate;
    }

    public Integer getIspackage() {
        return ispackage;
    }

    public void setIspackage(Integer ispackage) {
        this.ispackage = ispackage;
    }

    public String getFrontpicture() {
        return frontpicture;
    }

    public void setFrontpicture(String frontpicture) {
        this.frontpicture = frontpicture;
    }

    public Date getShangtime() {
        return shangtime;
    }

    public void setShangtime(Date shangtime) {
        this.shangtime = shangtime;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getAllsellnumber() {
        return allsellnumber;
    }

    public void setAllsellnumber(Integer allsellnumber) {
        this.allsellnumber = allsellnumber;
    }

    public Integer getUpdownstate() {
        return updownstate;
    }

    public void setUpdownstate(Integer updownstate) {
        this.updownstate = updownstate;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
