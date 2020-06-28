package com.example.ecommerce.dto;

import com.example.ecommerce.mbg.model.Chart;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: rain
 * @date: 2020/6/22 15:56
 * @description:
 */
public class ChartsParam {

    @ApiModelProperty(value = "自增Id")
    private String chartid;

    @ApiModelProperty(value = "用户ID")
    private String userid;

    @ApiModelProperty(value = "商品ID（要商品图片，价格，名字，属性）")
    private String goodid;

    @ApiModelProperty(value = "数量")
    private Integer number;

    @ApiModelProperty(value = "价钱")
    private BigDecimal price;

    @ApiModelProperty(value = "结算状态，0为false，1为true")
    private Integer checkstate;

    @ApiModelProperty(value = "商品的规格")
    private String attribute;

    @ApiModelProperty(value = "商品的名字")
    private String goodname;

    @ApiModelProperty(value = "商品的图片")
    private String frontpicture;

    @ApiModelProperty(value = "该商品是否包邮")
    private Integer ispackage;

    @ApiModelProperty(value = "sku的Id")
    private Integer skuid;

    @ApiModelProperty(value = "用户的地址")
    private String address;

    private static final long serialVersionUID = 1L;

    public String getChartid() {
        return chartid;
    }

    public void setChartid(String chartid) {
        this.chartid = chartid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGoodid() {
        return goodid;
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCheckstate() {
        return checkstate;
    }

    public void setCheckstate(Integer checkstate) {
        this.checkstate = checkstate;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getFrontpicture() {
        return frontpicture;
    }

    public void setFrontpicture(String frontpicture) {
        this.frontpicture = frontpicture;
    }

    public Integer getIspackage() {
        return ispackage;
    }

    public void setIspackage(Integer ispackage) {
        this.ispackage = ispackage;
    }

    public Integer getSkuid() {
        return skuid;
    }

    public void setSkuid(Integer skuid) {
        this.skuid = skuid;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
