package com.hh.service;


import com.hh.mapper.UserMapper;
import com.hh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public int addUser(User user){
        //是否存在userName
        List<User> userList = userMapper.findUserName(user.getUserName());
        if(userList.size() == 0){
            userMapper.addUser(user);
            return 1;
        }
        else{
            return 0;
        }
    }

    public int updateUser(User user){
        return userMapper.updateUser(user);
    }
}
