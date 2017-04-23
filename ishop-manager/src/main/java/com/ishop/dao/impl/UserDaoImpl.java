package com.ishop.dao.impl;

import com.ishop.dao.UserDao;
import com.ishop.pojo.User;
import com.ishop.util.DBUtil;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao on 2017/4/21 0021.
 */
public class UserDaoImpl implements UserDao {
    //添加方法
    public boolean add(User user){
        //数据库工具类
        DBUtil db = new DBUtil();
        //sql statement
        String sql = "insert into `user`(`name`,`password`,real_name,email,phone,created,updated) values(?,?,?,?,?,?,?)";
        //获取当前时间并设置
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setUpdated(new Timestamp(System.currentTimeMillis()));
        //sql 的参数
        Object[] params = {
                user.getName(),
                user.getPassword(),
                user.getRealName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreated(),
                user.getUpdated()
        };
        //rowNum表示影响行数,执行SQL
        int rowNum = db.doUpdate(sql,params);
        return rowNum == 1;
    }
    //更新方法
    public boolean update(User user){
        //数据库工具类
        DBUtil db = new DBUtil();
        //sql statement
        String sql = "update `user` set `name`=?,`password`=?,real_name=?,email=?,phone=?,updated=? where id=?";
        //获取当前时间并设置
        user.setUpdated(new Timestamp(System.currentTimeMillis()));
        //sql 的参数
        Object[] params = {
                user.getName(),
                user.getPassword(),
                user.getRealName(),
                user.getEmail(),
                user.getPhone(),
                user.getUpdated(),
                user.getId()
        };
        //rowNum表示影响行数,执行SQL
        int rowNum = db.doUpdate(sql,params);
        return rowNum == 1;
    }
    //根据Id删除
    public boolean delete(Integer id){
        //数据库工具类
        DBUtil db = new DBUtil();
        //sql statement
        String sql = "delete from `user` where id=?";
        //sql 的参数
        Object[] params = {id};
        //rowNum表示影响行数,执行SQL
        int rowNum = db.doUpdate(sql,params);
        return rowNum == 1;
    }
    //根据对象删除
    public boolean delete(User user){
        return delete(user.getId());
    }
    //根据Id获取对象
    public User get(Integer id){
        //数据库工具类
        DBUtil db = new DBUtil();
        //sql statement
        String sql = "select `name`,`password`,real_name,email,phone,created,updated from `user` where id =?";
        //sql 的参数
        Object[] params = {id};
        //rs表示查询结果集,执行SQL
        ResultSet rs = db.doQuery(sql,params);
        //查询返回的对象
        User user = new User();
        try {
            if (rs.next()){
                user.setName(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setRealName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setCreated(rs.getTimestamp(6));
                user.setUpdated(rs.getTimestamp(7));
                user.setId(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user ;

    }
    //根据name获取对象
    public User get(String name){
        //数据库工具类
        DBUtil db = new DBUtil();
        //sql statement
        String sql = "select id,`password`,real_name,email,phone,created,updated from `user` where name like ?";
        //sql 的参数
        Object[] params = {name};
        //rs表示查询结果集,执行SQL
        ResultSet rs = db.doQuery(sql,params);
        //查询返回的对象
        User user = new User();
        try {
            if (rs.next()){
                user.setId(rs.getInt(1));
                user.setPassword(rs.getString(2));
                user.setRealName(rs.getString(3));
                user.setEmail(rs.getString(3));
                user.setPhone(rs.getString(5));
                user.setCreated(rs.getTimestamp(6));
                user.setUpdated(rs.getTimestamp(7));
                user.setName(name);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user ;
    }
    //统计总条数
    public int getTotal(){

        //数据库工具类
        DBUtil db = new DBUtil();
        //sql statement
        String sql = "select count(id) from `user`";
        //rs表示查询结果集,执行SQL
        ResultSet rs = db.doQuery(sql);
        //查询返回的条数
        int count = 0;
        try {
            if (rs.next()){
                count = rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count ;
    }
    //查询列表
    public List<User> list(){
        return list(0,Short.MAX_VALUE);
    }
    /**
     * @param start 开始位置
     * @param count 数量
     * @return start到count范围的对象
     */
    public List<User> list(int start, int count){
        //返回的列表
        List<User> list = new ArrayList<User>();
        //数据库工具类
        DBUtil db = new DBUtil();
        //sql statement
        String sql = "select * from `user` order by id limit ?,?";
        Object[] params = {start,count};
        //rs表示查询结果集,执行SQL
        ResultSet rs = db.doQuery(sql,params);
        try {
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRealName(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPhone(rs.getString(6));
                user.setCreated(rs.getTimestamp(7));
                user.setUpdated(rs.getTimestamp(8));
                list.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list ;

    }
    //根据Id判断对象是否存在
    public  boolean exists(Integer id){
        //数据库工具类
        DBUtil db = new DBUtil();
        //sql statement
        String sql = "select id from `user` where id =?";
        //sql 的参数
        Object[] params = {id};
        //rs表示查询结果集,执行SQL
        ResultSet rs = db.doQuery(sql,params);
        //查询返回的对象
        try {
            if (rs.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    //根据管理员名判断对象是否存在
    public  boolean exists(String name){
        //数据库工具类
        DBUtil db = new DBUtil();
        //sql statement
        String sql = "select id from `user` where `name` like ?";
        //sql 的参数
        Object[] params = {name};
        //rs表示查询结果集,执行SQL
        ResultSet rs = db.doQuery(sql,params);
        try {
            if (rs.next()){
               return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    //根据登录名和密码获取对象
    public User get(String name, String password){
        //数据库工具类
        DBUtil db = new DBUtil();
        //sql statement
        String sql = "select id,real_name,email,phone,created,updated from user where `name`=? and `password`=?";
        //sql 的参数
        Object[] params = {name,password};
        //rs表示查询结果集,执行SQL
        ResultSet rs = db.doQuery(sql,params);
        //查询返回的对象
        //查询返回的对象
        User user = new User();
        try {
            if (rs.next()){
                user.setId(rs.getInt(1));
                user.setName(name);
                user.setPassword(password);
                user.setRealName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPhone(rs.getString(4));
                user.setCreated(rs.getTimestamp(5));
                user.setUpdated(rs.getTimestamp(6));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user ;
    }

}
