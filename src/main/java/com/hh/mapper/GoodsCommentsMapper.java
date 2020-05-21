package com.hh.mapper;

import com.hh.pojo.GoodsComments;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsCommentsMapper {
    /**
     * 获取一个物品的评价
     * @param goodsId 物品ID
     * @return 评价
     */
    List<GoodsComments> getCommentsByGoodsId(int goodsId);

    /**
     * 添加一个评价
     * @param goodsComments 评价
     * @return 添加的记录条数
     */
    int addGoodsComments(GoodsComments goodsComments);

    /**
     * 删除一个评价
     * @param id 评价的ID
     * @return 被操作的记录条数
     */
    int deleteGoodsComments(int id);
}
