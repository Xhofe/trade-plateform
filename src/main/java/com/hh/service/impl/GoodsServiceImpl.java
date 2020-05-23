package com.hh.service.impl;

import com.hh.mapper.GoodsMapper;
import com.hh.pojo.Goods;
import com.hh.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {
    private GoodsMapper goodsMapper;

    @Autowired
    public void setGoodsMapper(GoodsMapper goodsMapper){
        this.goodsMapper = goodsMapper;
    }


    @Override
    public int addGood(Goods goods) {
        return goodsMapper.addGoods(goods);
    }

    @Override
    public int updateGood(Goods goods) {
        return updateGood(goods);
    }

    @Override
    public int deleteGood(int goodsId) {
        return deleteGood(goodsId);
    }

    @Override
    public boolean haveGoods(int userId, int goodsId) {
        Goods goods=goodsMapper.getGoodsByGoodsId(goodsId);
        return goods.getUserId()==userId;
    }
}
