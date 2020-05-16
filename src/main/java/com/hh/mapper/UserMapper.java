package com.hh.mapper;

import com.hh.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {
    List<User> getAllUsers();
    User getUserById(int userId);
    int addUser(User user);
    int updateUser(Map<String,Object> map);
    int deleteUser(int userId);
}
