package com.lion.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.lion.dao.ext.UserDao;
import com.lion.support.BaseDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author DJ
 * @date 2017/10/16.
 */
public class UserDaoTest extends BaseDaoTest{
    @Autowired
    UserDao userDao;

    @Test
    @DatabaseSetup(value="/dbUnitData/user_test.xml")
    public void testUserDao(){

    }
}
