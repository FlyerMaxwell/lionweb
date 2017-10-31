package com.lion.dao.ext;

import com.lion.dao.gen.ProjectPublicationMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/31.
 */
@Repository
public interface ProjectPublicationDao extends ProjectPublicationMapper{

    List<Long> selectPubIdByProId(@Param("proId") Long proId);

    int deleteRecordByPubId(@Param("pubId") Long pubId,@Param("proId") Long proId);

}
