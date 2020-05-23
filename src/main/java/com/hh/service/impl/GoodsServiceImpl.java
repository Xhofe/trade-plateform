package com.hh.service.impl;

import com.hh.mapper.GoodsMapper;
import com.hh.pojo.Goods;
import com.hh.pojo.Type;
import com.hh.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import java.util.List;

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
        return goodsMapper.updateGoods(goods);
    }

    @Override
    public int deleteGood(int goodsId) {
        return goodsMapper.deleteGoods(goodsId);
    }

    @Override
    public boolean haveGoods(int userId, int goodsId) {
        Goods goods=goodsMapper.getGoodsByGoodsId(goodsId);
        return goods.getUserId()==userId;
    }

    @Override
    public List<Goods> searchGoods(Map map) {
        return goodsMapper.SearchGoods(map);
    }

    @Override
    public List<Type> getAllType() {
        return goodsMapper.getAllType();
    }
}
