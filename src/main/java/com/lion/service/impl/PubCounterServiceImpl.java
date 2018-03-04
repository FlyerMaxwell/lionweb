package com.lion.service.impl;

import com.lion.dao.ext.PubCounterDao;
import com.lion.entity.PubCounter;
import com.lion.service.PubCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DJ
 * @date 2018/2/27.
 */
@Service("PubCounterService")
public class PubCounterServiceImpl implements PubCounterService{

    @Autowired
    PubCounterDao pubCounterDao;

    public PubCounter selectCountByPubId(Long pubId) {
        return pubCounterDao.selectCountByPubId(pubId);
    }

    public int updatePubCounter(PubCounter pubCounter) {
        return pubCounterDao.updateByPrimaryKey(pubCounter);
    }

    public int addPubCounter(PubCounter pubCounter) {
        return pubCounterDao.insertSelective(pubCounter);
    }
}
