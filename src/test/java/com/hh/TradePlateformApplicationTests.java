package com.hh;

import com.hh.mapper.UserMapper;
import com.hh.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class TradePlateformApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    void testUser(){
        User user=new User();
        user.setUserName("xhf");
        user.setUserPassword("666");
        user.setUserEmail("xhf@hh.com");
        userMapper.addUser(user);
    }

    @Test
    void testUpdateUser(){
//        Map<String,Object> map=new HashMap<>();
//        map.put("userId",1);
//        map.put("userName","lggqii");
//        System.out.println(userMapper.updateUser(map));
        User user=new User();
        user.setUserId(5);
        user.setUserPassword("666hhh");
        System.out.println(userMapper.updateUser(user));
    }
}