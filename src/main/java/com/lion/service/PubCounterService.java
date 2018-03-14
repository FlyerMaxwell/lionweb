package com.lion.service;

import com.lion.entity.PubCounter;

/**
 * @author DJ
 * @date 2018/2/27.
 */
public interface PubCounterService {

    PubCounter selectCountByPubId(Long pubId);

    int updatePubCounter(PubCounter pubCounter);

    int addPubCounter(PubCounter pubCounter);

    int deletePubCounter(Long pubId);
}
