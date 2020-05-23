package com.hh;


import com.hh.mapper.GoodsMapper;
import com.hh.pojo.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class goodTest {
    @Autowired
    GoodsMapper goodsMapper;

    @Test
    void testAddGood(){
        Goods good = new Goods();
//        good.setGoodsId(1);
        good.setGoodsName("测试商品2");
        good.setPop(1);
        good.setPrice(999);
        good.setSecondPrice(899);
        good.setUserId(1);
        good.setIntroduce("测试商品描述");
        good.setStatus(1);
        good.setType("测试商品类型");
        good.setImgurl("/img/url");
        System.out.println(goodsMapper.addGoods(good));
    }

    @Test
    void testUpdateGood(){

    }

    @Test
    void testDeleteGood(){

    }
}
