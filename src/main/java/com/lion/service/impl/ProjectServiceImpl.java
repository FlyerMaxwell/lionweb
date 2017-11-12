package com.lion.service.impl;

import com.lion.dao.ext.ProjectDao;
import com.lion.entity.Project;
import com.lion.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/31.
 */
@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    ProjectDao projectDao;

    public List<Project> listAllProject() {
        return projectDao.selectAllProject();
    }

    public List<Project> listProjectByUserId(Long userId) {
        return projectDao.selectProjectByUserId(userId);
    }

    public List<Project> listLatestPro(int num) {
        return projectDao.selectLatestProject(num);
    }

    public int addNewProject(Project project) {
        return projectDao.insertSelective(project);
    }

    public Project getProjectById(Long id) {
        return projectDao.selectByPrimaryKey(id);
    }

    public int editProject(Project project) {
        return projectDao.updateByPrimaryKeySelective(project);
    }

    public int deleteProject(Long id) {
        return projectDao.deleteByPrimaryKey(id);
    }
}
