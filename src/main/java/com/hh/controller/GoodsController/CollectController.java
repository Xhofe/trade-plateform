package com.hh.controller.GoodsController;

import com.hh.controller.BaseController;
import com.hh.pojo.Collect;
import com.hh.pojo.UserDetails;
import com.hh.service.CollectService;
import com.hh.util.ResponseStatus;
import com.hh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/goods/collect")
public class CollectController extends BaseController {

    private CollectService collectService;

    @Autowired
    public void setService(CollectService service) {
        this.collectService = service;
    }

    @GetMapping("has")
    public Object hasCollect(@RequestParam("goodsId")int goodsId, HttpServletRequest request){
        UserDetails userDetails = getUserDetails(request);
        if (userDetails == null) {
            return ResultUtil.fail(ResponseStatus.NO_LOGIN);
        }
        Collect collect=new Collect();
        collect.setGoodsId(goodsId);
        collect.setUserId(userDetails.getUserId());
        collect=collectService.hasCollect(collect);
        boolean has=collect!=null;
        Map<String ,Object> map=new HashMap<>();
        map.put("has",has);
        if (has){
            map.put("collectId",collect.getCollectId());
        }
        return ResultUtil.ok(map);
    }

    @GetMapping("add")
    public Object addCollect(@RequestParam("goodsId")int goodsId,HttpServletRequest request){
        UserDetails userDetails = getUserDetails(request);
        if (userDetails == null) {
            return ResultUtil.fail(ResponseStatus.NO_LOGIN);
        }
        Collect collect=new Collect();
        collect.setUserId(userDetails.getUserId());
        collect.setGoodsId(goodsId);
        Collect collect1=collectService.hasCollect(collect);
        if (collect1!=null){
            return ResultUtil.fail(ResponseStatus.HAS_COLLECT);
        }
        int res=collectService.addCollect(collect);
        collect=collectService.hasCollect(collect);
        if (res==1){
            return ResultUtil.ok(collect.getCollectId());
        }else {
            return ResultUtil.error();
        }
    }

    @GetMapping("cancel")
    public Object cancelCollect(@RequestParam("collectId")int collectId,HttpServletRequest request){
        UserDetails userDetails = getUserDetails(request);
        if (userDetails == null) {
            return ResultUtil.fail(ResponseStatus.NO_LOGIN);
        }
        int res=collectService.deleteCollect(collectId);
        if (res==1){
            return ResultUtil.ok();
        }else {
            return ResultUtil.error();
        }
    }
}
