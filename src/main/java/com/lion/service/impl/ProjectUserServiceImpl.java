package com.lion.service.impl;

import com.lion.dao.ext.ProjectUserDao;
import com.lion.entity.ProjectUser;
import com.lion.service.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/31.
 */
@Service("ProjectUserService")
public class ProjectUserServiceImpl implements ProjectUserService{

    @Autowired
    ProjectUserDao projectUserDao;

    public List<Long> listUserIdByProId(Long id) {
        return projectUserDao.selectUserIdByProId(id);
    }

    public int deleteRecordById(Long userId, Long proId) {
        return projectUserDao.deleteRecordById(userId,proId);
    }

    public int addRecord(ProjectUser record) {
        return projectUserDao.insertSelective(record);
    }

}
