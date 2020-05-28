package com.hh.service.impl;


import com.hh.mapper.LeaveMapper;
import com.hh.pojo.Leave;
import com.hh.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {
    private LeaveMapper leaveMapper;

    @Autowired
    public void setLeaveMapper(LeaveMapper leaveMapper){
        this.leaveMapper = leaveMapper;
    }

    @Override
    public int addLeave(Leave leave) {
        return leaveMapper.addLeave(leave);
    }

    @Override
    public int updateLeave(Leave leave) {
        return updateLeave(leave);
    }

    @Override
    public int deleteLeave(int leaveId) {
        return deleteLeave(leaveId);
    }

    @Override
    public boolean haveLeave(int userId, int leaveId) {
        List<Leave> leaves = leaveMapper.getLeaveByUserId(userId);
        for (Leave leave:leaves){
            if(leave.getLeaveId() == leaveId)
                return true;
        }
        return false;
    }
}
