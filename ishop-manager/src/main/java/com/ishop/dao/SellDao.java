package com.ishop.dao;

import com.ishop.pojo.Sell;

import java.util.List;

/**
 * Created by tao on 2017/4/20 0020.
 */
public interface SellDao {
    //添加方法
    public boolean add(Sell sell);
    //更新方法
    public boolean update(Sell sell);
    //根据Id删除
    public boolean delete(Integer id);
    //根据Id获取对象
    public Sell get(Integer id);
    //统计总条数
    public int getTotal();
    //查询列表
    public List<Sell> list();
    //查询列表
    public List<Sell> list(int start,int count);
    //根据Id判断对象是否存在
    public  boolean exists(Integer id);
    //根据管理员名判断对象是否存在
    public  boolean exists(String name);
    //根据登录名和密码获取对象
    public  Sell get(String name,String password);
}