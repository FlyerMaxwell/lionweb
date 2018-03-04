package com.lion.dao.gen;

import com.lion.entity.PubCounter;

public interface PubCounterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PubCounter record);

    int insertSelective(PubCounter record);

    PubCounter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PubCounter record);

    int updateByPrimaryKey(PubCounter record);
}