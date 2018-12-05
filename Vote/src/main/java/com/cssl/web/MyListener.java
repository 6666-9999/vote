package com.cssl.web;

import com.cssl.pojo.Users;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
import java.util.Set;

@WebListener
public class MyListener implements HttpSessionListener, ServletContextListener {

    private ServletContext application;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        application=sce.getServletContext();

        Set<String> set=new HashSet<>();
        set.add("默认");
        application.setAttribute("set",set);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session=se.getSession();
        session.setMaxInactiveInterval(3*60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session=se.getSession();
        Users users=(Users)session.getAttribute("user");
        Set<String> set=(Set<String>) application.getAttribute("set");
        if(users!=null){
            if(set.contains(users.getUsername())){
                set.remove(users.getUsername());
                application.setAttribute("set",set);
            }
        }

    }
}
