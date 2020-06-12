package com.hh.service.impl;

import com.hh.enums.OrderStatus;
import com.hh.mapper.GoodsMapper;
import com.hh.mapper.OrderMapper;
import com.hh.pojo.Collect;
import com.hh.pojo.Goods;
import com.hh.pojo.Order;
import com.hh.pojo.User;
import com.hh.service.CollectService;
import com.hh.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;
    private CollectService collectService;
    private GoodsMapper goodsMapper;

    @Autowired
    public void setGoodsMapper(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setCollectService(CollectService collectService) {
        this.collectService = collectService;
    }

    @Override
    public int receiveOrder(int id) {
        Order order=getOrderById(id);
        order.setStatus(OrderStatus.FINISH.getCode());
        return orderMapper.updateOrder(order);
    }

    @Override
    public int shipOrder(int id) {
        Order order = getOrderById(id);
        order.setStatus(OrderStatus.TO_BE_RECEIVED.getCode());
        return orderMapper.updateOrder(order);
    }

    @Override
    public int refundOrder(int id) {
        Order order=getOrderById(id);
        order.setStatus(OrderStatus.REFUND_REQUEST.getCode());
        return orderMapper.updateOrder(order);
    }

    @Override
    public int cancelOrder(int id) {
        Order order=getOrderById(id);
        order.setStatus(OrderStatus.CANCEL.getCode());
        return orderMapper.updateOrder(order);
    }



    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderMapper.getOrdersByUserId(userId);
    }

    @Override
    public List<Order> getOrdersByUserIdAndGoodsId(int userId, int goodsId) {
        return orderMapper.getOrdersByUserIdAndGoodsId(userId,goodsId);
    }

    @Override
    public String  creatOrder(User user) {
        List<Collect> collects=collectService.getCollectsByUserId(user.getUserId()).stream()
                .filter(collect -> collect.getChecked()==1).collect(Collectors.toList());
        if (collects.size()==0){
            return "0";
        }
        StringBuilder sb=new StringBuilder();
        for (Collect collect : collects) {
            if (collect.getCount()==0)continue;
            Goods goods=goodsMapper.getGoodsByGoodsId(collect.getGoodsId());
            if (goods.getNum()<collect.getCount()){
                log.info(collect.getGoodsId()+"库存不足");
                sb.append(collect.getCollectId()).append(",");
                continue;
            };
            Order order=new Order();
            order.setGoodsId(goods.getGoodsId());
            order.setBuyUserId(user.getUserId());
            order.setAddress(user.getUserAddress());
            order.setName(user.getUserName());
            order.setCost(collect.getCount()*goods.getSecondPrice());
            order.setStatus(OrderStatus.TO_BE_PAID.getCode());
            order.setTime(new Timestamp(System.currentTimeMillis()));
            goods.setNum(goods.getNum()-collect.getCount());
            goodsMapper.updateGoods(goods);
            orderMapper.addOrder(order);
            collectService.deleteCollect(collect.getCollectId());
        }
        return sb.toString();
    }

    @Override
    public Order getOrderById(int id) {
        return orderMapper.getOrderById(id);
    }
}
