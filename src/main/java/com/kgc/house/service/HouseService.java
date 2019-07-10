package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.utils.HouseCondition;
import com.kgc.house.utils.HouseParam;
import com.kgc.house.utils.PageParam;

import java.util.Map;

public interface HouseService {
    int add(House house);

    PageInfo<House> getPageHouse(Integer id, PageParam pageParam);

    int delete(String id);

    int update(House house);

    House getHouse(String id);

    int deleteHouse(String id, Integer isdel);

    PageInfo<House> getPageIspassHouse(HouseParam houseParam);

    int getIsPass(Integer ispass,String id);

    int upBacheIsPass(Map<String,Object> map);
    PageInfo<House> getPageHouse(HouseCondition condition);
}
