package com.hh.controller.GoodsController;


import com.hh.controller.BaseController;
import com.hh.pojo.Goods;
import com.hh.pojo.UserDetails;
import com.hh.service.GoodsService;
import com.hh.util.CookieUtil;
import com.hh.util.ResponseStatus;
import com.hh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/goods")
public class ReleaseGoodsController extends BaseController {
    private GoodsService goodsService;

    @Autowired
    public void setGoodsService(GoodsService goodsService){
        this.goodsService = goodsService;
    }

    @PostMapping("/addGoods")
    public Object addGoods(@RequestBody Goods goods, HttpServletRequest request){
        try {
            UserDetails userDetails = getUserDetails(request);
            if (userDetails == null) {
                return ResultUtil.fail(ResponseStatus.NO_LOGIN);
            }
            int userId = userDetails.getUserId();
            goods.setUserId(userId);
            goodsService.addGood(goods);
            return ResultUtil.ok();
        }catch (Exception e){
            return ResultUtil.error();
        }
    }

    @PostMapping("/updateGoods")
    public Object updateGoods(@RequestBody Goods goods,HttpServletRequest request){
        try {
            UserDetails userDetails = getUserDetails(request);
            if (userDetails == null) {
                return ResultUtil.fail(ResponseStatus.NO_LOGIN);
            }
            int userId = userDetails.getUserId();
            goods.setUserId(userId);

            if(goodsService.haveGoods(userId,goods.getGoodsId())){
                if(goodsService.updateGood(goods) == 1)
                    return ResultUtil.ok();
                else
                    return ResultUtil.fail(ResponseStatus.PARAM_ERROR);
            }
            else
                return ResultUtil.fail(ResponseStatus.FORBIDDEN);

        }catch (Exception e){
            return ResultUtil.error();
        }
    }

    @PostMapping("/deleteGoods")
    public Object deleteGoods(@RequestBody Goods goods,HttpServletRequest request){
        try {
            UserDetails userDetails = getUserDetails(request);
            if (userDetails == null) {
                return ResultUtil.fail(ResponseStatus.NO_LOGIN);
            }
            int userId = userDetails.getUserId();
            goods.setUserId(userId);

            if(goodsService.haveGoods(userId,goods.getGoodsId())){
                if(goodsService.deleteGood(goods.getGoodsId()) == 1)
                    return ResultUtil.ok();
                else
                    return ResultUtil.fail(ResponseStatus.PARAM_ERROR);
            }
            else
                return ResultUtil.fail(ResponseStatus.FORBIDDEN);
        }catch (Exception e){
            return ResultUtil.error();
        }
    }
}
