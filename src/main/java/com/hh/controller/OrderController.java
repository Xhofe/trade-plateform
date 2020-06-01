package com.hh.controller;

import com.hh.enums.ResponseStatus;
import com.hh.pojo.Order;
import com.hh.pojo.UserDetails;
import com.hh.service.OrderService;
import com.hh.service.UserService;
import com.hh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController extends BaseController {

    private UserService userService;
    private OrderService orderService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("buy")
    public Object buy(HttpServletRequest request){
        UserDetails userDetails = getUserDetails(request);
        if (userDetails == null) {
            return ResultUtil.fail(ResponseStatus.NO_LOGIN);
        }
        String res=orderService.creatOrder(userService.getUserById(userDetails.getUserId()));
        if (res.length()==0){
            return ResultUtil.ok("购买成功");
        }
        return ResultUtil.ok(res.substring(0,res.length()-1)+"库存不足");
    }

    @GetMapping("list")
    public Object list(HttpServletRequest request){
        UserDetails userDetails = getUserDetails(request);
        if (userDetails == null) {
            return ResultUtil.fail(ResponseStatus.NO_LOGIN);
        }
        List<Order> orders=orderService.getOrdersByUserId(userDetails.getUserId());
        return ResultUtil.ok(orders);
    }
}
