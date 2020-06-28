package com.example.ecommerce.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author: rain
 * @date: 2020/6/23 15:03
 * @description:
 */
public class CommentParam {

    @ApiModelProperty("评论")
    private String comment;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("属性")
    private String attribute;

    @ApiModelProperty("数量")
    private int num;


    public int getNum() {
        return num;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getComment() {
        return comment;
    }

    public String getUsername() {
        return username;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
