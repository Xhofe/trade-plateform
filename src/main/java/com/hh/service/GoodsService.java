package com.hh.service;

import com.hh.pojo.Goods;

public interface GoodsService {
    //添加
    public int addGood(Goods goods);

    //更新
    public int updateGood(Goods goods);

    //删除
    public int deleteGood(int goodsId);

    /**
     * 判断用户是否拥有此商品
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @return 拥有?
     */
    public boolean haveGoods(int userId,int goodsId);
}
