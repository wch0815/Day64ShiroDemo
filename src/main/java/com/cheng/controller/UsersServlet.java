package com.cheng.controller;

import com.cheng.pojo.Users;
import com.cheng.service.IUsersService;
import com.cheng.service.impl.UsersServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IUsersService usersService = new UsersServiceImpl();

        String user = request.getParameter("user");
        String pass = request.getParameter("password");

        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user,pass);

        try {
            subject.login(token);
        } catch (AuthenticationException e){
            response.sendRedirect("index.jsp");
            e.printStackTrace();
        }

        if (subject.isAuthenticated()){
            response.sendRedirect("success.jsp");
        }

        //  subject.hasRole(角色名) 判断用户是否拥有指定角色
        System.out.println(subject.hasRole("校长"));
        //  subject.isPermitted(权限名)判断用户是否拥有指定权限
        System.out.println(subject.isPermitted("学生"));



//        Users users = usersService.getUserByLogin(user, pass);
//        System.out.println(users);
//        if (users != null && users.getUid() != 0){
//            request.getRequestDispatcher("success.jsp").forward(request,response);
//        }else {
//            request.getRequestDispatcher("index.jsp").forward(request,response);
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
