package com.hh.service;

import com.hh.pojo.Collect;

public interface CollectService {
    public Collect hasCollect(Collect collect);
    public int addCollect(Collect collect);
    public int deleteCollect(int id);
}
