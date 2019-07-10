package com.kgc.house.pageController;

import com.kgc.house.entity.Street;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("Controller2")
@RequestMapping("/page/")
public class StreetController {
    @Autowired
    private StreetService streetService;

    @RequestMapping("getAllStreet")
    @ResponseBody
    public List<Street> getAllStreet(Integer did){
        return   streetService.getAllStreet(did);
    }
}
