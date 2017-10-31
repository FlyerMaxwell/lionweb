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
}
