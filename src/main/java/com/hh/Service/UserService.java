package com.hh.Service;


import com.hh.mapper.UserMapper;
import com.hh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public User addUser(User user){
        userMapper.addUser(user);
        return user;
    }
}
