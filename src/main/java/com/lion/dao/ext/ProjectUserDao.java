package com.lion.dao.ext;

import com.lion.dao.gen.ProjectUserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/31.
 */
@Repository
public interface ProjectUserDao extends ProjectUserMapper{

    List<Long> selectUserIdByProId(@Param("proId") Long proId);

    int deleteRecordById(@Param("userId") Long userId,@Param("proId") Long proId);

    int deleteRecordByProId(@Param("proId") Long proId);

    int deleteRecordByUserId(@Param("userId") Long userId);
}
