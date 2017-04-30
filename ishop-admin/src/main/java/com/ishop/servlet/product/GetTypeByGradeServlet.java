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
import java.util.List;


/**
 * Created by myq on 2017/4/23.
 */

@WebServlet(name = "GetTypeByGradeServlet", urlPatterns = "/getTypeByGrade")
public class GetTypeByGradeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TypeDao td = new TypeDaoImpl();
            String gradeParam = request.getParameter("grade");
            Integer grade = Integer.parseInt(gradeParam);
            Type type = new Type();
            List<Type> list = td.getParentList(grade);
            PrintWriter out = response.getWriter();
            String json = JSON.toJSONString(list, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect);
            if (list != null) {
                out.println(json);
                out.close();
            } else {
                out.println(json);
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
