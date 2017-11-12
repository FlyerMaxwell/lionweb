package com.lion.dao.ext;

import com.lion.dao.gen.NewsMapper;
import com.lion.entity.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/31.
 */
@Repository
public interface NewsDao extends NewsMapper{

    List<News> selectAllNews();

    List<News> selectNewsByUsername(@Param("username") String username);

    List<News> selectLatestNews(@Param("num") int num);

}
