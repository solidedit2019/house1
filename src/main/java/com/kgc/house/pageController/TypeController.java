package com.kgc.house.pageController;

import com.kgc.house.entity.Type;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("pageController")
@RequestMapping("/page/")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("getAllType")
    @ResponseBody
    public  List<Type> getAllType(){
        List<Type> types = typeService.allType();
        return types;
    }

}
