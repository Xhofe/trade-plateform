package com.hh.mapper;

import com.hh.pojo.Leave;

import java.util.List;

public interface LeaveMapper {
    List<Leave> getLeaveByGoodsId(int goodsId);
    List<Leave> getLeaveByUserId(int userId);
    int addLeave(Leave leave);
    int deleteLeave(int leaveId);
}
