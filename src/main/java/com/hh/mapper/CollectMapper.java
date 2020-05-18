package com.hh.mapper;

import com.hh.pojo.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectMapper {
    List<Collect> getCollectsByUserId(int userId);
    int deleteCollectById(int id);
    int addCollect(Collect collect);
}
