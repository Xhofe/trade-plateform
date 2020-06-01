package com.hh.service;

import com.hh.pojo.Order;
import com.hh.pojo.User;

import java.util.List;

public interface OrderService {
    /**
     * 获取一个用户的订单
     * @param userId 用户Id
     * @return 订单列表
     */
    public List<Order> getOrdersByUserId(int userId);

    /**
     * 获取用户指定商品的订单
     * @param userId 用户Id
     * @param goodsId 商品Id
     * @return 订单列表
     */
    public List<Order> getOrdersByUserIdAndGoodsId(int userId,int goodsId);

    public Order getOrderById(int id);

    public String creatOrder(User user);
}
