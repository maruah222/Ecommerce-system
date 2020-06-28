package com.example.ecommerce.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author: rain
 * @date: 2020/6/24 21:21
 * @description:
 */
public class AddSkuParam {

    @ApiModelProperty(value = "商品Id")
    private String goodid;

    @ApiModelProperty(value = "数量")
    private Integer number;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "Vip价格")
    private BigDecimal vipprice;

    @ApiModelProperty(value = "库存量")
    private Integer leftNumber;

    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "属性")
    private String attribute;

    private static final long serialVersionUID = 1L;

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

    public BigDecimal getVipprice() {
        return vipprice;
    }

    public void setVipprice(BigDecimal vipprice) {
        this.vipprice = vipprice;
    }

    public Integer getLeftNumber() {
        return leftNumber;
    }

    public void setLeftNumber(Integer leftNumber) {
        this.leftNumber = leftNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
