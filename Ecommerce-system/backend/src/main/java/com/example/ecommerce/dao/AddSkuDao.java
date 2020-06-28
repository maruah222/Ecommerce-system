package com.example.ecommerce.dao;

import com.example.ecommerce.mbg.model.GoodSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: rain
 * @date: 2020/6/15 16:13
 * @description:
 */

@Mapper
public interface AddSkuDao {

    //批量添加sku'
    int InsertList(@Param("list")List<GoodSku> list);


}
