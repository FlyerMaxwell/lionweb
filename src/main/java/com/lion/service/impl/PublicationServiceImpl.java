package com.lion.service.impl;

import com.lion.dao.ext.PublicationDao;
import com.lion.entity.Publication;
import com.lion.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/21.
 */
@Service("PublicationService")
public class PublicationServiceImpl implements PublicationService{
    @Autowired
    PublicationDao publicationDao;

    public List<Publication> listAllPublication(){
        return publicationDao.selectAllPublication();
    }

    public int addNewPublication(Publication publication) {
        return publicationDao.insertSelective(publication);
    }
}
