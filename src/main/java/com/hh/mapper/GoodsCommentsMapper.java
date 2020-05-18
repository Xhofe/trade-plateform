package com.hh.mapper;

import com.hh.pojo.GoodsComments;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsCommentsMapper {
    List<GoodsComments> getCommentsByGoodsId(int goodsId);
    int addGoodsComments(GoodsComments goodsComments);
    int deleteGoodsComments(int id);
}
