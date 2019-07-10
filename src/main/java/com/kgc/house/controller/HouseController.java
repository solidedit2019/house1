package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.service.HouseService;
import com.kgc.house.utils.HouseParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("HouseController")
@RequestMapping("/admin/")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping("getPass")
    public Map<String, Object> getYesPass(HouseParam houseParam) {
        PageInfo<House> pageInfo = houseService.getPageIspassHouse(houseParam);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return map;
    }

    @RequestMapping("isPassHouse")
    public String isPassHouse(String id, Integer ispass) {
        int result = houseService.getIsPass(ispass, id);
        return "{\"result\":" + result + "}";
    }

    @RequestMapping("upAllisPassHouse")
    public String upAllisPassHouse(Integer ispass,String id) {
        Map<String, Object> map=new HashMap<>();
        String[] idstr = id.split(",");
        List<String> list = Arrays.asList(idstr);
        map.put("list",list);
        map.put("ispass",ispass);
        int result = houseService.upBacheIsPass(map);
        return "{\"result\":" + result + "}";
    }
}
