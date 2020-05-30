package com.hh.service.impl;

import com.hh.mapper.CollectMapper;
import com.hh.pojo.Collect;
import com.hh.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    private CollectMapper mapper;

    @Autowired
    public void setMapper(CollectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int deleteCollect(int id) {
        return mapper.deleteCollectById(id);
    }

    @Override
    public int addCollect(Collect collect) {
        return mapper.addCollect(collect);
    }

    @Override
    public Collect hasCollect(Collect collect) {
        List<Collect> collects=mapper.getCollectsByUserId(collect.getUserId());
        for (Collect collect1 : collects) {
            if (collect.getGoodsId()==collect1.getGoodsId()){
                return collect1;
            }
        }
        return null;
    }
}
