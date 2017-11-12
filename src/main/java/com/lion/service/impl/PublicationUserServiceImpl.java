package com.lion.service.impl;

import com.lion.dao.ext.PublicationUserDao;
import com.lion.entity.PublicationUser;
import com.lion.service.PublicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/30.
 */
@Service("PublicationUserService")
public class PublicationUserServiceImpl implements PublicationUserService{

    @Autowired
    PublicationUserDao publicationUserDao;

    public int addRecord(PublicationUser record) {
        return publicationUserDao.insertSelective(record);
    }

    public List<Long> listUserIdByPubId(Long id) {
        return publicationUserDao.selectUserIdByPubId(id);
    }

    public int deleteRecordById(Long userId,Long pubId) {
        return publicationUserDao.deleteRecordById(userId,pubId);
    }

    public int deleteRecordByPubId(Long pubId) {
        return publicationUserDao.deleteRecordByPubId(pubId);
    }

    public int deleteRecordByUserId(Long userId) {
        return publicationUserDao.deleteRecordByUserId(userId);
    }
}
