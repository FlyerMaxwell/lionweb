package com.lion.dao.ext;

import com.lion.dao.gen.PublicationMapper;
import com.lion.entity.Publication;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/21.
 */
@Repository
public interface PublicationDao extends PublicationMapper{
    List<Publication> selectAllPublication();

    List<Publication> selectPublicationByUserId(@Param("userId") Long userId);

    List<Publication> selectLatestPublication(@Param("num") int num);

}
