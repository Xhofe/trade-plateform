package com.hh.service;

import com.hh.pojo.GoodsComments;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsCommentsService {
    public List<GoodsComments> getCommentsByGoodsId(int goodsId);
}
