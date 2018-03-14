package com.lion.dao.ext;

import com.lion.dao.gen.ProCounterMapper;
import com.lion.entity.ProCounter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author DJ
 * @date 2018/2/26.
 */
@Repository
public interface ProCounterDao extends ProCounterMapper{

    ProCounter selectCountByProId(@Param("proId")Long proId);

    int deleteByProId(@Param("proId") Long proId);
}
