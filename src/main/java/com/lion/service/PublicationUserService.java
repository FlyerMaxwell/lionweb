package com.lion.service;

import com.lion.entity.PublicationUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/30.
 */
public interface PublicationUserService {
    int addRecord(PublicationUser record);

    List<Long> listUserIdByPubId(Long id);

    int deleteRecordById(Long userId,Long pubId);

    int deleteRecordByPubId(Long pubId);

    int deleteRecordByUserId(Long userId);
}
