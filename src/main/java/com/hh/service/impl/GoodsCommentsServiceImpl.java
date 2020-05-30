package com.hh.service.impl;

import com.hh.mapper.GoodsCommentsMapper;
import com.hh.pojo.GoodsComments;
import com.hh.service.GoodsCommentsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GoodsCommentsServiceImpl implements GoodsCommentsService {

    private GoodsCommentsMapper mapper;

    @Autowired
    public void setMapper(GoodsCommentsMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<GoodsComments> getCommentsByGoodsId(int goodsId) {
        return mapper.getCommentsByGoodsId(goodsId);
    }
}
