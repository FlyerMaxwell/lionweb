package com.lion.dao.ext;

import com.lion.dao.gen.ProjectMapper;
import com.lion.entity.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/30.
 */
@Repository
public interface ProjectDao extends ProjectMapper{
    List<Project> selectAllProject();

    List<Project> selectProjectByUserId(@Param("userId") Long userId);
}
