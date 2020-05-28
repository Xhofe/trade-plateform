package com.hh.controller.GoodsController;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hh.controller.BaseController;
import com.hh.pojo.Goods;
import com.hh.pojo.UserDetails;
import com.hh.service.GoodsService;
import com.hh.util.ResponseStatus;
import com.hh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/goods")
public class ReleaseGoodsController extends BaseController {
    private GoodsService goodsService;


    @Autowired
    public void setGoodsService(GoodsService goodsService){
        this.goodsService = goodsService;
    }

    @GetMapping("/category")
    public Object getCategory(){
        return ResultUtil.ok(goodsService.getAllType());
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
            return ResultUtil.ok(goods);
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
                    return ResultUtil.ok(goods);
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
                    return ResultUtil.ok(goods);
                else
                    return ResultUtil.fail(ResponseStatus.PARAM_ERROR);
            }
            else
                return ResultUtil.fail(ResponseStatus.FORBIDDEN);
        }catch (Exception e){
            return ResultUtil.error();
        }
    }

    @PostMapping("/searchGoods")
    public Object searchGoods(@RequestBody JSONObject jsonObject, HttpServletRequest request){
        try {
            UserDetails userDetails = getUserDetails(request);
            if(userDetails==null)
                return ResultUtil.fail(ResponseStatus.NO_LOGIN);
            int userId = userDetails.getUserId();
            Map map = new HashMap();

            JSONArray jsonArray = jsonObject.getJSONArray("keywords");
            List<String> keywords = JSONObject.parseArray(jsonArray.toJSONString(),String.class);

            map.put("keywords",keywords);
            map.put("typeId",jsonObject.getIntValue("typeId"));
            map.put("priceLow",jsonObject.getDoubleValue("priceLow"));
            map.put("priceHigh",jsonObject.getDoubleValue("priceHigh"));
            map.put("secondPriceLow",jsonObject.getDoubleValue("secondPriceLow"));
            map.put("secondPriceHigh",jsonObject.getDoubleValue("secondPriceHigh"));

            List<Goods> goods = goodsService.searchGoods(map);
            if(goods.size() != 0){
                return ResultUtil.ok(goods);
            }
            else
                return ResultUtil.fail(ResponseStatus.NO_GOODS);
        }catch (Exception e){
            return ResultUtil.error();
        }
    }

    @GetMapping("product")
    public Object product(){
        List<Goods> goodsList=goodsService.getAllGoods();
        Map<String,Object> res=new HashMap<>();
        res.put("count",String.valueOf(goodsList.size()));
        res.put("goods",goodsList);
        return ResultUtil.ok(res);
    }
}
