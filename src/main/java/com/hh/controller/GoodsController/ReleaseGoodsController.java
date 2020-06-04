package com.hh.controller.GoodsController;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hh.controller.BaseController;
import com.hh.pojo.Goods;
import com.hh.pojo.Type;
import com.hh.pojo.UserDetails;
import com.hh.service.GoodsService;
import com.hh.enums.ResponseStatus;
import com.hh.util.PathUtil;
import com.hh.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/goods")
public class ReleaseGoodsController extends BaseController {
    private GoodsService goodsService;


    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @ApiOperation("获取分类列表")
    @GetMapping("/category")
    public Object getCategory() {
        return ResultUtil.ok(goodsService.getAllType());
    }

    @PostMapping("/addGoods")
    public Object addGoods(@RequestBody Goods goods, HttpServletRequest request) {
        try {
            UserDetails userDetails = getUserDetails(request);
            if (userDetails == null) {
                return ResultUtil.fail(ResponseStatus.NO_LOGIN);
            }
            if (userDetails.admin = false) {
                return ResultUtil.fail(ResponseStatus.NO_ADMIN);
            }
            int userId = userDetails.getUserId();
            goodsService.addGood(goods);
            return ResultUtil.ok(goods);
        } catch (Exception e) {
            return ResultUtil.error();
        }
    }

    @PostMapping("/updateGoods")
    public Object updateGoods(@RequestBody Goods goods, HttpServletRequest request) {
        try {
            UserDetails userDetails = getUserDetails(request);
            if (userDetails == null) {
                return ResultUtil.fail(ResponseStatus.NO_LOGIN);
            }
            if (userDetails.admin = false) {
                return ResultUtil.fail(ResponseStatus.NO_ADMIN);
            }
            if (goodsService.updateGood(goods) == 1)
                return ResultUtil.ok(goods);
            else
                return ResultUtil.fail(ResponseStatus.PARAM_ERROR);

        } catch (Exception e) {
            return ResultUtil.error();
        }
    }

    @PostMapping("/deleteGoods")
    public Object deleteGoods(@RequestBody Goods goods, HttpServletRequest request) {
        try {
            UserDetails userDetails = getUserDetails(request);
            if (userDetails == null) {
                return ResultUtil.fail(ResponseStatus.NO_LOGIN);
            }
            if (userDetails.admin = false) {
                return ResultUtil.fail(ResponseStatus.NO_ADMIN);
            }
            if (goodsService.deleteGood(goods.getGoodsId()) == 1)
                return ResultUtil.ok(goods);
            else
                return ResultUtil.fail(ResponseStatus.PARAM_ERROR);
        } catch (Exception e) {
            return ResultUtil.error();
        }
    }

    @PostMapping("/searchGoods")
    public Object searchGoods(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        try {
            UserDetails userDetails = getUserDetails(request);
            if (userDetails == null)
                return ResultUtil.fail(ResponseStatus.NO_LOGIN);
            int userId = userDetails.getUserId();
            Map map = new HashMap();

            JSONArray jsonArray = jsonObject.getJSONArray("keywords");
            List<String> keywords = JSONObject.parseArray(jsonArray.toJSONString(), String.class);

            map.put("keywords", keywords);
            map.put("typeId", jsonObject.getIntValue("typeId"));
            map.put("priceLow", jsonObject.getDoubleValue("priceLow"));
            map.put("priceHigh", jsonObject.getDoubleValue("priceHigh"));
            map.put("secondPriceLow", jsonObject.getDoubleValue("secondPriceLow"));
            map.put("secondPriceHigh", jsonObject.getDoubleValue("secondPriceHigh"));

            List<Goods> goods = goodsService.searchGoods(map);
            if (goods.size() != 0) {
                return ResultUtil.ok(goods);
            } else
                return ResultUtil.fail(ResponseStatus.NO_GOODS);
        } catch (Exception e) {
            return ResultUtil.error();
        }
    }

    @GetMapping("product")
    public Object product() {
        List<Goods> goodsList = goodsService.getAllGoods();
        Map<String, Object> res = new HashMap<>();
        res.put("count", String.valueOf(goodsList.size()));
        res.put("goods", goodsList);
        return ResultUtil.ok(res);
    }

    @GetMapping("goodsInfo")
    public Object goodsInfo(@RequestParam("id") int id){
        Goods goods=goodsService.getGoodsById(id);
        if (goods==null)return ResultUtil.fail(ResponseStatus.NO_GOODS);
        return ResultUtil.ok(goods);
    }


    @Value("${file.img.path}")
    private String imgFilePath;


    @PostMapping("uploadImg")
    public Object uploadImg(@RequestParam(value="file") MultipartFile file){
        System.out.println("img file path is"+imgFilePath);
        if(!file.isEmpty()){
            try {
                JSONObject res = new JSONObject();
                JSONObject resUrl = new JSONObject();
                String filename = UUID.randomUUID().toString().replaceAll("-", "");
                String ext = FilenameUtils.getExtension(file.getOriginalFilename());
                String filenames = filename + "." + ext;
                String pathname = PathUtil.getRootPath() +imgFilePath+ filenames;
                file.transferTo(new File(pathname));
                resUrl.put("src", filenames);
                res.put("msg", "");
                res.put("code", 0);
                res.put("data", resUrl);
                return res;
            }catch (IOException e){
                e.printStackTrace();
                return ResultUtil.fail(ResponseStatus.PARAM_ERROR);
            }
        }
        else{
            return ResultUtil.fail(ResponseStatus.PARAM_ERROR);
        }
    }

    @ApiOperation("获取商品列表")
    @GetMapping("/list")
    @ResponseBody
    public Object list() {
        Map<String,Object> res = new HashMap<>();

        List<Goods> goodsList = goodsService.getAllGoods();
        res.put("code",0);
        res.put("msg","成功");
        res.put("count",goodsList.size());
        res.put("data",goodsList);
        return res;
    }

}
