package com.lion.dao.ext;

import com.lion.dao.gen.PubCounterMapper;
import com.lion.entity.PubCounter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author DJ
 * @date 2018/2/26.
 */
@Repository
public interface PubCounterDao extends PubCounterMapper{

    PubCounter selectCountByPubId(@Param("pubId")Long pubId);

    int deleteByPubId(@Param("pubId") Long pubId);
}
