package com.lion.service;

import com.lion.entity.ProCounter;

/**
 * @author DJ
 * @date 2018/2/27.
 */
public interface ProCounterService {

    ProCounter selectCountByProId(Long proId);

    int updateProCounter(ProCounter proCounter);

    int addProCounter(ProCounter proCounter);

    int deleteProCounter(Long proId);
}
