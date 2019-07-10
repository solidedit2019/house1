package com.kgc.house.mapper;

import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseExample;
import com.kgc.house.utils.HouseCondition;
import com.kgc.house.utils.HouseParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HouseMapper {
    long countByExample(HouseExample example);

    int deleteByExample(HouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseExample example);

    int updateByExample(@Param("record") House record, @Param("example") HouseExample example);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    List<House> getListHouse(Integer id);

    House getHouse(String id);

    List<House> getHousePass(HouseParam houseParam);

    int upbatchIspassHouse(Map<String, Object> map);

    List<House> getPageHouse(HouseCondition condition);

}