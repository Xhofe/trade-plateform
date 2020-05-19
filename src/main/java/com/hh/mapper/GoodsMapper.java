package com.hh.mapper;

import com.hh.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface GoodsMapper {
    /**
     * 获取所有物品
     * @return 物品列表
     */
    List<Goods> getAllGoods();

    /**
     * 根据条件搜索物品
     * @param map 条件键值对
     * @return 物品列表
     * TODO
     */
    List<Goods> SearchGoods(Map map);

    /**
     * 添加物品
     * @param goods 物品
     * @return 添加的记录条数
     */
    int addGoods(Goods goods);

    /**
     * 修改物品信息
     * @param goods 物品
     * @return 被操作的记录条数
     */
    int updateGoods(Goods goods);

    /**
     * 删除一个物品
     * @param goodsId 物品ID
     * @return 被操作的记录条数
     */
    int deleteGoods(int goodsId);
}
