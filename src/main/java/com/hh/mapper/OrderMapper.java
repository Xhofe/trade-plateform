package com.hh.mapper;

import com.hh.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    List<Order> getOrdersByUserId(int userId);
    int addOrder(Order order);
    int deleteOrder(int orderId);
}
