package com.hh.mapper;

import com.hh.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    /**
     * 获取两个人的对话
     * @param user1Id 用户1
     * @param user2Id 用户2
     * @return 对话列表
     */
    List<Message> getMessageByUserId(@Param("buyId") int user1Id, @Param("sellId") int user2Id);

    /**
     * 添加一条消息
     * @param message 消息
     * @return 添加的记录条数
     */
    int addMessage(Message message);
}
