package com.kgc.house.pageController;

import com.kgc.house.entity.Users;
import com.kgc.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("pageUserController")
@RequestMapping("/page/")
public class UsersController {
    @Autowired
    UsersService usersService;
    @RequestMapping("getUserName")
    @ResponseBody
    public String getUserName(String name){
        int result = usersService.getUsersByName(name);
        return "{\"result\":"+result+"}";
    }
    @RequestMapping("register")
    public String getUsersByNotAdmin(Users users){
        int result = usersService.addUsersNotAdmin(users);
        if(result>0){
            return "login";
        }else {
             return "regs";
        }


    }
    @RequestMapping("loginAction")
    public String loginAction(String veryCode,String name, String password, HttpSession session, Model model){
        String code = (String) session.getAttribute("code");
        if(veryCode.equals(code)){

        if(name!=null&&password!=null&&!name.equals("")&&!password.equals("")){
            Users users = usersService.login(name, password);
            session.setAttribute("users",users);
            session.setMaxInactiveInterval(100);
            return "redirect:getHouse";
        }else {
            model.addAttribute("info","用户密码账号不正确");
            return "login";
        }

    }else {
        model.addAttribute("info","验证码失效或过期");
        return "login";
        }
}
}
