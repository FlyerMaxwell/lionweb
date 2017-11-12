package com.lion.dao.ext;

import com.lion.dao.gen.PublicationUserMapper;
import com.lion.entity.Publication;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/30.
 */
@Repository
public interface PublicationUserDao extends PublicationUserMapper{

    List<Long> selectUserIdByPubId(@Param("pubId") Long pubId);

    int deleteRecordById(@Param("userId") Long userId,@Param("pubId") Long pubId);

    int deleteRecordByPubId(@Param("pubId") Long pubId);

    int deleteRecordByUserId(@Param("userId") Long userId);
}
