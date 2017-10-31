package com.lion.dao.gen;

import com.lion.entity.PublicationUser;

public interface PublicationUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PublicationUser record);

    int insertSelective(PublicationUser record);

    PublicationUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PublicationUser record);

    int updateByPrimaryKey(PublicationUser record);
}