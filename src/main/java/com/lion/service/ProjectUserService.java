package com.lion.service;

import com.lion.entity.ProjectUser;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/31.
 */
public interface ProjectUserService {
    List<Long> listUserIdByProId(Long id);

    int deleteRecordById(Long userId,Long proId);

    int addRecord(ProjectUser record);
}
