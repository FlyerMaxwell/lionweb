package com.lion.service;

import com.lion.entity.ProjectPublication;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/31.
 */
public interface ProjectPublicationService {

    List<Long> listPubIdByProId(Long id);

    int deleteRecordByPubId(Long pubId,Long proId);

    int addRecord(ProjectPublication record);

}
