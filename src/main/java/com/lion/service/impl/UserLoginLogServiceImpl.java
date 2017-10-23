package com.lion.service.impl;

import com.lion.dao.ext.UserLoginLogDao;
import com.lion.entity.UserLoginLog;
import com.lion.service.UserLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DJ
 * @date 2017/10/16.
 */
@Service("userLoginLogService")
public class UserLoginLogServiceImpl implements UserLoginLogService{

    @Autowired
    UserLoginLogDao userLoginLogDao;

    public void addUserLoginLog(UserLoginLog loginLog) {
        userLoginLogDao.insertSelective(loginLog);
    }
}
