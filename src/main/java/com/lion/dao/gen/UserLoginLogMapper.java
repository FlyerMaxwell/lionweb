package com.lion.dao.gen;

import com.lion.entity.UserLoginLog;

public interface UserLoginLogMapper {
    int deleteByPrimaryKey(Long loginLogId);

    int insert(UserLoginLog record);

    int insertSelective(UserLoginLog record);

    UserLoginLog selectByPrimaryKey(Long loginLogId);

    int updateByPrimaryKeySelective(UserLoginLog record);

    int updateByPrimaryKey(UserLoginLog record);
}