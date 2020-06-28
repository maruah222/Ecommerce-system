package com.example.ecommerce.mbg.mapper;

import com.example.ecommerce.mbg.model.Goodsuprecord;
import com.example.ecommerce.mbg.model.GoodsuprecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsuprecordMapper {
    long countByExample(GoodsuprecordExample example);

    int deleteByExample(GoodsuprecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goodsuprecord record);

    int insertSelective(Goodsuprecord record);

    List<Goodsuprecord> selectByExample(GoodsuprecordExample example);

    Goodsuprecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goodsuprecord record, @Param("example") GoodsuprecordExample example);

    int updateByExample(@Param("record") Goodsuprecord record, @Param("example") GoodsuprecordExample example);

    int updateByPrimaryKeySelective(Goodsuprecord record);

    int updateByPrimaryKey(Goodsuprecord record);
}