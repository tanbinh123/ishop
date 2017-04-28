package com.ishop.servlet.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ishop.dao.TypeDao;
import com.ishop.dao.impl.TypeDaoImpl;
import com.ishop.pojo.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tao on 2017/4/28 0028.
 */
@WebServlet(name = "ExistsTypeByNameServlet",urlPatterns = "/existsTypeByName")
public class ExistsTypeByNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取参数
            String name = request.getParameter("name");
            //查询
            TypeDao td = new TypeDaoImpl();
            boolean result  = td.exists(name);
            PrintWriter out = response.getWriter();
            out.println(result);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
