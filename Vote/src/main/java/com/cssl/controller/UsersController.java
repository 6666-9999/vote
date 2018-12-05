package com.cssl.controller;

import com.cssl.pojo.Users;
import com.cssl.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class UsersController implements ServletContextAware {

    private ServletContext application;

    @Autowired
    private UsersService us;

    @RequestMapping("/log")
    public String login(Users users, Model model, HttpSession session){
        Users user=us.login(users);
        Set<String> set=(Set<String>) application.getAttribute("set");
        if(user==null) {
            model.addAttribute("message","~登录失败，用户名或密码错误！~");
            return "login";
        }else if(set.contains(user.getUsername())){
            model.addAttribute("message","~用户已登录~");
            return "login";
        }else{
            set.add(user.getUsername());
            application.setAttribute("set",set);
            session.setAttribute("user",user);
            return "forward:/page";
        }
    }

    @RequestMapping("/register")
    public String register(Users users){
        if(us.register(users)){
            return "login";
        }
        return "regist";
    }

    @RequestMapping("/isExists")
    @ResponseBody
    public boolean isExists(String username){
        return us.isExists(username);
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.invalidate();
        return "login";
    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.application=servletContext;
    }
}
