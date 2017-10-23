package com.lion.dao.gen;


import com.lion.entity.Publication;

public interface PublicationMapper {
    int deleteByPrimaryKey(Long pubId);

    int insert(Publication record);

    int insertSelective(Publication record);

    Publication selectByPrimaryKey(Long pubId);

    int updateByPrimaryKeySelective(Publication record);

    int updateByPrimaryKey(Publication record);
}