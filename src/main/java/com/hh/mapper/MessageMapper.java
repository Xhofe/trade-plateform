package com.hh.mapper;

import com.hh.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    List<Message> getMessageByUserId(@Param("user1Id") int user1Id, @Param("user2Id") int user2Id);
    int addMessage(Message message);
}
