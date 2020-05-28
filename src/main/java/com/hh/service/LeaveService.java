package com.hh.service;

import com.hh.pojo.Leave;

public interface LeaveService {
    //添加
    public int addLeave(Leave leave);

    //更新
    public int updateLeave(Leave leave);

    //删除
    public int deleteLeave(int leaveId);
}
