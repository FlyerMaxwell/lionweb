package com.lion.service.impl;

import com.lion.dao.ext.ProjectPublicationDao;
import com.lion.entity.ProjectPublication;
import com.lion.service.ProjectPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/31.
 */
@Service("ProjectPublicationService")
public class ProjectPublicationServiceImpl implements ProjectPublicationService{

    @Autowired
    ProjectPublicationDao projectPublicationDao;

    public List<Long> listPubIdByProId(Long id) {
        return projectPublicationDao.selectPubIdByProId(id);
    }

    public int deleteRecordByPubId(Long pubId,Long proId) {
        return projectPublicationDao.deleteRecordByPubId(pubId,proId);
    }

    public int addRecord(ProjectPublication record) {
        return projectPublicationDao.insertSelective(record);
    }


}
