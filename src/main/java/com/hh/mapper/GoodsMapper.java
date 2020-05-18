package com.hh.mapper;

import com.hh.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface GoodsMapper {
    List<Goods> getAllGoods();
    // TODO
    List<Goods> SearchGoods(Map map);
    int addGoods(Goods goods);
    int updateGoods(Goods goods);
    int deleteGoods(int goodsId);
}
