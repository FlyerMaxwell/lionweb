package com.lion.service;

import com.lion.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/31.
 */
public interface ProjectService {
    List<Project> listAllProject();

    List<Project> listProjectByUserId(Long userId);

    int addNewProject(Project project);

    Project getProjectById(Long id);

    int editProject(Project project);

    int deleteProject(Long id);
}
