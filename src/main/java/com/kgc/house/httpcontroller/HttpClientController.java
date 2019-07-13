package com.kgc.house.httpcontroller;

import com.kgc.house.utils.VerifyCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController("httpClientController")
@RequestMapping("/page/")
public class HttpClientController {

    @RequestMapping("getcode")
    public String getcode(String smsMob, HttpSession session) {
        //四位数随机验证码
        String text = (int)(Math.random() * 10000) + "";
        String smsText = "验证码是：" + text + "请保管好你的验证码";
        //发送验证码短信
       int i = VerifyCodeUtil.verifyCode(smsMob, smsText);
        //将验证码存入session
        session.setAttribute("code",text);
        session.setMaxInactiveInterval(120);//120秒
        return "{\"result\":" + i + "}";
    
    }
}