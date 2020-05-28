package com.hh.service;

import com.hh.pojo.Leave;
import com.sun.el.parser.BooleanNode;

public interface LeaveService {
    //添加
    public int addLeave(Leave leave);

    //更新
    public int updateLeave(Leave leave);

    //删除
    public int deleteLeave(int leaveId);

    //用户是否拥有该评论
    public boolean haveLeave(int userId,int leaveId);
}
