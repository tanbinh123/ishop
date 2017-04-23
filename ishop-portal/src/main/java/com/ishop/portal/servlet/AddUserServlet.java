package com.ishop.portal.servlet;

import com.ishop.dao.UserDao;
import com.ishop.dao.impl.UserDaoImpl;
import com.ishop.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tao on 2017/4/23 0023.
 */
@WebServlet(name = "AddUserServlet",urlPatterns = "/addUser")
public class AddUserServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try{
            //获取表单提交的参数
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String realName = request.getParameter("realName");
            //设置User对象
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setRealName(realName);
            user.setEmail(email);
            user.setPhone(phone);
            //插入数据库
            UserDao ad = new UserDaoImpl();
            boolean result = ad.add(user);
            String redrictPage = "index.jsp";
            if (result == true) {
                //注册成功
                request.getSession().setAttribute("user",user);
                redrictPage = "sucess.jsp";
            }else {
                redrictPage = "failure.jsp";
            }
            response.sendRedirect(redrictPage);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}