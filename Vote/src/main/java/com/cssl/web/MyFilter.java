package com.cssl.web;

import com.cssl.pojo.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpSession session=req.getSession();
        String uri = req.getRequestURI();
        //System.out.println("uri:"+uri);

        Users users=(Users)session.getAttribute("user");
        //System.out.println("user:"+users);
        if(uri.endsWith("login.html")||uri.endsWith("log")||uri.contains("images/")||uri.contains("css/")||uri.contains("js/")||uri.endsWith("regist.html")||uri.endsWith("register")||uri.contains("isExists")) {
            chain.doFilter(request,response);
        }else if(users==null){
            //System.out.println("user:"+users);
            HttpServletResponse resp=(HttpServletResponse)response;
            req.getRequestDispatcher("login.html").forward(req,resp);
        }else{
            chain.doFilter(request,response);
        }
    }
}
