package com.lion.service.impl;

import com.lion.dao.ext.ProCounterDao;
import com.lion.entity.ProCounter;
import com.lion.service.ProCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DJ
 * @date 2018/2/27.
 */
@Service("ProCounterService")
public class ProCounterServiceImpl implements ProCounterService{

    @Autowired
    ProCounterDao proCounterDao;

    public ProCounter selectCountByProId(Long proId) {
        return proCounterDao.selectCountByProId(proId);
    }

    public int updateProCounter(ProCounter proCounter) {
        return proCounterDao.updateByPrimaryKey(proCounter);
    }

    public int addProCounter(ProCounter proCounter) {
        return proCounterDao.insertSelective(proCounter);
    }
}
