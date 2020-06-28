package com.example.ecommerce.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Goodsuprecord implements Serializable {
    @ApiModelProperty(value = "自增Id")
    private Integer id;

    @ApiModelProperty(value = "商家Id")
    private String shopid;

    @ApiModelProperty(value = "商品Id")
    private String goodid;

    @ApiModelProperty(value = "状态，0是审核不通过，1是审核通过")
    private Integer state;

    @ApiModelProperty(value = "商品的名字")
    private String goodname;

    @ApiModelProperty(value = "审核的时间")
    private Date verifytime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getGoodid() {
        return goodid;
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public Date getVerifytime() {
        return verifytime;
    }

    public void setVerifytime(Date verifytime) {
        this.verifytime = verifytime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopid=").append(shopid);
        sb.append(", goodid=").append(goodid);
        sb.append(", state=").append(state);
        sb.append(", goodname=").append(goodname);
        sb.append(", verifytime=").append(verifytime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}