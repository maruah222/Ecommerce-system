package com.example.ecommerce.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

public class Chart implements Serializable {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", chartid=").append(chartid);
        sb.append(", userid=").append(userid);
        sb.append(", goodid=").append(goodid);
        sb.append(", number=").append(number);
        sb.append(", price=").append(price);
        sb.append(", checkstate=").append(checkstate);
        sb.append(", attribute=").append(attribute);
        sb.append(", goodname=").append(goodname);
        sb.append(", frontpicture=").append(frontpicture);
        sb.append(", ispackage=").append(ispackage);
        sb.append(", skuid=").append(skuid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}