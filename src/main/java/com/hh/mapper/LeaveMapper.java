package com.hh.mapper;

import com.hh.pojo.Leave;

import java.util.List;

public interface LeaveMapper {
    /**
     * 获取一个物品的所有留言
     * @param goodsId 物品ID
     * @return 留言列表
     */
    List<Leave> getLeaveByGoodsId(int goodsId);

    /**
     * 获取一个用户的所有留言
     * @param userId 用户ID
     * @return 留言列表
     */
    List<Leave> getLeaveByUserId(int userId);

    /**
     * 添加一条留言
     * @param leave 留言
     * @return 添加的记录条数
     */
    int addLeave(Leave leave);

    /**
     * 删除一条留言
     * @param leaveId 留言
     * @return 被操作的记录条数
     */
    int deleteLeave(int leaveId);
}
