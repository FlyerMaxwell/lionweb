package com.lion.dao.ext;

import com.lion.dao.gen.LabelMapper;
import com.lion.entity.Label;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DJ
 * @date 2018/1/2.
 */
@Repository
public interface LabelDao extends LabelMapper{

    List<Label> selectAllLabel();

    Label selectFormer(@Param("rank") Long rank);

    Label selectLatter(@Param("rank") Long rank);
}
