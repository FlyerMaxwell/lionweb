package com.lion.dao.gen;

import com.lion.entity.ProCounter;

public interface ProCounterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProCounter record);

    int insertSelective(ProCounter record);

    ProCounter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProCounter record);

    int updateByPrimaryKey(ProCounter record);
}
