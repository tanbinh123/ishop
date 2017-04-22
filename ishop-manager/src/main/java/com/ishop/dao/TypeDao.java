package com.ishop.dao;

import com.ishop.pojo.Type;

import java.util.List;

/**
 * Created by tao on 2017/4/19 0019.
 */
public interface TypeDao {
    //添加方法
    public boolean add(Type type);
    //根据Id删除
    public boolean delete(Integer id);
    //更新方法
    public boolean update(Type type);
    //根据Id获取对象
    public Type get(Integer id);
    //统计总条数
    public int getTotal();
    //查询列表
    public List<Type> list();
    //查询列表
    public List<Type> list(int start,int count);
    //根据Id判断对象是否存在
    public  boolean exists(Integer id);
}