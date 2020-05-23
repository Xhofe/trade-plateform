package com.hh;


import com.hh.mapper.GoodsMapper;
import com.hh.pojo.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class goodTest {
    @Autowired
    GoodsMapper goodsMapper;

    @Test
    void testAddGoods(){
        Goods good = new Goods();
//        good.setGoodsId(1);
        good.setGoodsName("测试商品2");
        good.setPop(1);
        good.setPrice(999);
        good.setSecondPrice(899);
        good.setUserId(1);
        good.setIntroduce("测试商品描述");
        good.setStatus(1);
        good.setTypeId(1);
        good.setImgurl("/img/url");
        System.out.println(goodsMapper.addGoods(good));
    }

    @Test
    void testUpdateGoods(){
        Goods good = new Goods();
        good.setGoodsId(4);
        good.setGoodsName("测试商品改");
        good.setPop(1);
        good.setPrice(999);
        good.setSecondPrice(899);
        good.setUserId(1);
        good.setIntroduce("测试商品描述");
        good.setStatus(1);
        good.setTypeId(1);
        good.setImgurl("/img/url");
        System.out.println(goodsMapper.updateGoods(good));

    }

    @Test
    void testDeleteGoods(){
        int goodsId = 4;
        System.out.println(goodsMapper.deleteGoods(goodsId));
    }

    @Test
    void testSearchGoods(){
        Map map = new HashMap();
        List<String> list = new ArrayList<>();
        list.add("1");
        map.put("keywords",list);
        System.out.println(goodsMapper.SearchGoods(map));
    }
}
