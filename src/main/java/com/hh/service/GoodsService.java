package com.hh.service;

import com.hh.pojo.Goods;

public interface GoodsService {
    //添加
    public int addGood(Goods goods);

    //更新
    public int updateGood(Goods goods);

    //删除
    public int deleteGood(int goodsId);
}
