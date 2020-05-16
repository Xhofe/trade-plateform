package com.hh.mapper;

import com.hh.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AdminMapper {
    List<Admin> getAllAdmins();
    Admin getAdminById(int id);
    int addAdmin(Admin admin);
    int deleteAdmin(int id);
    int updateAdmin(Map map);
}
