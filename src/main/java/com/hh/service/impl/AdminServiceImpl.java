package com.hh.service.impl;

import com.hh.mapper.AdminMapper;
import com.hh.pojo.Admin;
import com.hh.service.AdminService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public class AdminServiceImpl implements AdminService {
    private AdminMapper adminMapper;

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public List<Admin> getAdmins() {
        return adminMapper.getAllAdmins();
    }
}
