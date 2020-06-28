package com.example.ecommerce.mbg.mapper;

import com.example.ecommerce.mbg.model.Loginrecord;
import com.example.ecommerce.mbg.model.LoginrecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginrecordMapper {
    long countByExample(LoginrecordExample example);

    int deleteByExample(LoginrecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Loginrecord record);

    int insertSelective(Loginrecord record);

    List<Loginrecord> selectByExample(LoginrecordExample example);

    Loginrecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Loginrecord record, @Param("example") LoginrecordExample example);

    int updateByExample(@Param("record") Loginrecord record, @Param("example") LoginrecordExample example);

    int updateByPrimaryKeySelective(Loginrecord record);

    int updateByPrimaryKey(Loginrecord record);
}