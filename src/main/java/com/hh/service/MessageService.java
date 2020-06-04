package com.hh.service;

import com.hh.pojo.Message;

import java.util.List;

public interface MessageService {
    public int sendMessage(Message message);
    public List<Message> getList(int userId);
}
