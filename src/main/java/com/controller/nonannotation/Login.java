package com.controller.nonannotation;

import com.bean.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class Login extends BaseController{

    @RequestMapping(value = "/toLogin.mvc")
    public String toLogin(){
        System.out.println("dl");
        return "login";
    }

    /**
     *  登录方法
     * @param userName
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/login.mvc")
    public String login(@RequestParam("userName")String userName,@RequestParam("pwd")String pwd){
        System.out.println("join in login method!!");
        HttpSession session = null;
        if(userName.equals("muQingFeng") && pwd.equals("123456")){
            User user = new User(userName,pwd);
            session = getRequest().getSession();
            session.setAttribute("user",user);
            return "hello";
        }else{
            return "login";
        }
    }

    /**
     *  退出登录
     * @return
     */
    @RequestMapping(value = "/signOut.mvc")
    public String signOut(){
        System.out.println("join in signOut method!!");
        getSession().setAttribute("user",null);
        return "login";
    }

}
