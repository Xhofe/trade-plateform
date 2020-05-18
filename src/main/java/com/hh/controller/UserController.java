package com.hh.controller;


import com.alibaba.fastjson.JSONObject;
import com.hh.service.UserService;
import com.hh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public Object addUser(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();

        if(userService.addUser(user) == 1){
            jsonObject.put("msg",1);
        }
        else{
            jsonObject.put("msg",0);
        }
        return jsonObject.toJSONString();
    }

    @PostMapping("/updateUser")
    public Object updateUser(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();

        if(userService.updateUser(user) ==1)
            jsonObject.put("msg",1);
        else
            jsonObject.put("msg",0);

        return jsonObject.toJSONString();
    }
}
