package com.example.ecommerce.dao;

import com.example.ecommerce.mbg.model.Userr;

import java.util.List;

/**
 * @author: rain
 * @date: 2020/6/26 12:22
 * @description:
 */
public interface ManagerDao {
    List<Userr> SelectUserBeVIP();
}
