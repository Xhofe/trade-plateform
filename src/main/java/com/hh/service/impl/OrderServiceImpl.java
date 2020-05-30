package com.hh.service.impl;

import com.hh.mapper.OrderMapper;
import com.hh.pojo.Order;
import com.hh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderMapper mapper;

    @Autowired
    public void setMapper(OrderMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return mapper.getOrdersByUserId(userId);
    }

    @Override
    public List<Order> getOrdersByUserIdAndGoodsId(int userId, int goodsId) {
        return mapper.getOrdersByUserIdAndGoodsId(userId,goodsId);
    }

    @Override
    public Order getOrderById(int id) {
        return mapper.getOrderById(id);
    }
}
