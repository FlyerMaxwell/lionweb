package com.lion.dao.ext;

import com.lion.dao.gen.PublicationMapper;
import com.lion.entity.Publication;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/21.
 */
@Repository
public interface PublicationDao extends PublicationMapper{
    List<Publication> selectAllPublication();
}
