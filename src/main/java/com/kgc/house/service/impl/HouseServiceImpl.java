package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.mapper.HouseMapper;
import com.kgc.house.service.HouseService;
import com.kgc.house.utils.HouseCondition;
import com.kgc.house.utils.HouseParam;
import com.kgc.house.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper mapper;
    @Override
    public int add(House house) {
        return mapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getPageHouse(Integer id, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        List<House> listHouse = mapper.getListHouse(id);

        return new PageInfo<>(listHouse);
    }

    @Override
    public int delete(String id) {

        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(House house) {

        return mapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public House getHouse(String id) {
        return mapper.getHouse(id);
    }

    @Override
    public int deleteHouse(String id, Integer isdel) {
          House house=new House();
          house.setId(id);
          house.setIsdel(isdel);
        return  mapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getPageIspassHouse( HouseParam houseParam) {
        PageHelper.startPage(houseParam.getPage(),houseParam.getRows());

        List<House> houses = mapper.getHousePass(houseParam);
        return new PageInfo<>(houses);
    }

    @Override
    public int getIsPass(Integer ispass, String id) {
        House house=new House();
        house.setIspass(ispass);
        house.setId(id);
            return  mapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int upBacheIsPass(Map<String, Object> map) {

        return mapper.upbatchIspassHouse(map);
    }

    @Override
    public PageInfo<House> getPageHouse(HouseCondition condition) {
          PageHelper.startPage(condition.getPage(),condition.getRows());
        List<House> pageHouse = mapper.getPageHouse(condition);
        return new PageInfo<>(pageHouse);
    }


}
