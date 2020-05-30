package com.hh.controller.GoodsController;

import com.hh.controller.BaseController;
import com.hh.pojo.Comments;
import com.hh.pojo.Order;
import com.hh.pojo.UserDetails;
import com.hh.service.CommentsService;
import com.hh.service.OrderService;
import com.hh.util.ResponseStatus;
import com.hh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/goods/comments/")
public class CommentsController extends BaseController {
    private CommentsService commentsService;
    private OrderService orderService;

    @Autowired
    public void setCommentsService(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{goodsId}")
    public Object getComments(@PathVariable String goodsId){
        try {
            List<Comments> comments= commentsService.getCommentsByGoodsId(Integer.parseInt(goodsId));
            return ResultUtil.ok(comments);
        }catch (Exception e){
            return ResultUtil.error();
        }
    }

    @PostMapping("add")
    public Object addComments(@RequestBody Comments comments, HttpServletRequest request){
        UserDetails userDetails=getUserDetails(request);
        if (userDetails==null){
            return ResultUtil.fail(ResponseStatus.NO_LOGIN);
        }
        Order order=orderService.getOrderById(comments.getOrderId());
        if (order==null){
            return ResultUtil.fail(ResponseStatus.NO_BUY);
        }
        comments.setUserId(order.getBuyUserId());
        comments.setGoodsId(order.getGoodsId());
        int res= commentsService.addGoodsComment(comments);
        if (res!=1){
            return ResultUtil.error();
        }else {
            return ResultUtil.ok();
        }
    }
}
