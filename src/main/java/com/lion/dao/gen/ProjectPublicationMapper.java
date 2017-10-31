package com.lion.dao.gen;

import com.lion.entity.ProjectPublication;

public interface ProjectPublicationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectPublication record);

    int insertSelective(ProjectPublication record);

    ProjectPublication selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectPublication record);

    int updateByPrimaryKey(ProjectPublication record);
}