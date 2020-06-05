package com.hh.service;

import com.hh.pojo.Goods;
import com.hh.pojo.Type;
import com.sun.deploy.security.ValidationState;

import java.util.List;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    //添加
    public int addGood(Goods goods);

    //更新
    public int updateGood(Goods goods);

    //删除
    public int deleteGood(int goodsId);


    /**
     * 获取所有分类
     * @return 分类
     */
    public List<Type> getAllType();

    /**
     * 添加分类
     * @param type
     * @return
     */
    public int addType(Type type);

    public List<Goods> searchGoods(Map map);

    /**
     * 获取所有Goods
     * @return Goods
     */
    public List<Goods> getAllGoods();

    public Goods getGoodsById(int goodsId);

}
