package com.hh.controller.GoodsController;

import com.hh.controller.BaseController;
import com.hh.pojo.GoodsComments;
import com.hh.service.GoodsCommentsService;
import com.hh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/goods/comments")
public class GoodsCommentsController extends BaseController {
    private GoodsCommentsService service;

    @Autowired
    public void setService(GoodsCommentsService service) {
        this.service = service;
    }

    @GetMapping("/{goodsId}")
    public Object getComments(@PathVariable String goodsId){
        try {
            List<GoodsComments> comments=service.getCommentsByGoodsId(Integer.parseInt(goodsId));
            return ResultUtil.ok(comments);
        }catch (Exception e){
            return ResultUtil.error();
        }
    }
}
