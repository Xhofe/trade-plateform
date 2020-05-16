package com.hh;

import com.hh.mapper.UserMapper;
import com.hh.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

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
    void testMybatis(){
        List<User> userList=userMapper.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}