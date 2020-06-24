package com.example.ecommerce.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

public class OrderReturn implements Serializable {
    @ApiModelProperty(value = "自增Id")
    private Integer id;

    @ApiModelProperty(value = "订单ID")
    private String orderid;

    @ApiModelProperty(value = "总金额")
    private BigDecimal money;

    @ApiModelProperty(value = "用户ID")
    private String userid;

    @ApiModelProperty(value = "订单状态，0是待审核，1是审核通过，2是拒绝退款")
    private Integer orderstatus;

    private String goodid;

    @ApiModelProperty(value = "商家Id")
    private String shopid;

    @ApiModelProperty(value = "退换原因")
    private String returnreason;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

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

    public String getReturnreason() {
        return returnreason;
    }

    public void setReturnreason(String returnreason) {
        this.returnreason = returnreason;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderid=").append(orderid);
        sb.append(", money=").append(money);
        sb.append(", userid=").append(userid);
        sb.append(", orderstatus=").append(orderstatus);
        sb.append(", goodid=").append(goodid);
        sb.append(", shopid=").append(shopid);
        sb.append(", returnreason=").append(returnreason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}