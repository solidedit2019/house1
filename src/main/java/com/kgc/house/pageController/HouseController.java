package com.kgc.house.pageController;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.*;
import com.kgc.house.service.DistrictService;
import com.kgc.house.service.HouseService;
import com.kgc.house.service.StreetService;
import com.kgc.house.service.TypeService;
import com.kgc.house.utils.HouseCondition;
import com.kgc.house.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private DistrictService districtService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private HouseService houseService;

    @RequestMapping("goFaBu")
    public String goFaBu(Model model) {
        //绑定数据
        List<District> allDistrict = districtService.getAllDistrict();
        List<Type> types = typeService.allType();
        model.addAttribute("allDistrict", allDistrict);
        model.addAttribute("types", types);
        return "fabu";
    }

    @RequestMapping("getStreetByDid")
    @ResponseBody
    public List<Street> getStreetByDid(Integer id) {
        //绑定数据

        List<Street> allStreet = streetService.getAllStreet(id);
        return allStreet;
    }

    @RequestMapping("addHouse")
    public String addHouse(@RequestParam(name = "pfile", required = false) CommonsMultipartFile pfile, House house, HttpSession session) throws Exception {
        //上传图片
        //图片名称
        String filename = pfile.getOriginalFilename();
        //图片后缀名
        String expname = filename.substring(filename.lastIndexOf("."));
        //上传图片扩展名
        String path = System.currentTimeMillis() + expname;
        //上传图片
        File file = new File("C:/IDEA/img/" + path);
        if (!file.exists()) {
            file.mkdirs();
        }
        //上传文件
        pfile.transferTo(file);
        Users users = (Users) session.getAttribute("users");
        //编号
        house.setId(System.currentTimeMillis() + "");
        //用户编号
        house.setUserId(users.getId());
        //图片名称
        house.setPath(path);
        int add = houseService.add(house);
        if (add == 1) {
            return "redirect:getHouse";
        } else {
            file.delete();
            return "redirect:goFaBu";
        }

    }

    @RequestMapping("getpic")
    @ResponseBody
    public String getpic(@RequestParam(name = "pfile", required = false) CommonsMultipartFile pfile) throws Exception {
        String filename = pfile.getOriginalFilename();
        //图片后缀名
        String expname = filename.substring(filename.lastIndexOf("."));
        //上传图片扩展名
        String path = System.currentTimeMillis() + expname;
        //上传图片
        File file = new File("C:/IDEA/img/" + path);
        if (!file.exists()) {
            file.mkdirs();
        }
        pfile.transferTo(file);
        return path;
    }

    @RequestMapping("getHouse")
    public String getHouse(HttpSession session, PageParam pageParam, Model model) {
        Users users = (Users) session.getAttribute("users");
        PageInfo<House> pageHouse = houseService.getPageHouse(users.getId(), pageParam);
        model.addAttribute("pageHouse", pageHouse);
        return "guanli";
    }

    @RequestMapping("goUpdate")
    public String goUpdate(String id, Model model) {
        House house = houseService.getHouse(id);
        model.addAttribute("house", house);
        return "update";
    }
    @RequestMapping("updateHouse")
    public String updateHouse(MultipartFile pfile,House house)throws Exception{
        String filename = pfile.getOriginalFilename();
        if(!filename.equals("")){
            String exename = filename.substring(filename.lastIndexOf("."));
            String path = System.currentTimeMillis() + exename;
            pfile.transferTo(new File("C:/IDEA/img/"+path));
            new File("C:/IDEA/img/"+house.getPath()).delete();
            house.setPath(path);

        }
        int row = houseService.update(house);

        if(row>0){
            return "redirect:getHouse";
        }else {
            return "redirect:goUpdate";
        }
    }
        @RequestMapping("deleteHouse")
        @ResponseBody
        public String deleteHouse(String id){
            int i = houseService.deleteHouse(id, 1);

              return "{\"result\":"+i+"}";
        }

     @RequestMapping("getPageHouse")
    public String getPageHouse(HouseCondition condition,Model model){
         PageInfo<House> info = houseService.getPageHouse(condition);
         model.addAttribute("condition",condition);
         model.addAttribute("info",info);
         return "list";
     }
}
