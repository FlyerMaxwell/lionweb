package com.lion.service;

import com.lion.entity.News;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/31.
 */
public interface NewsService {

    List<News> listAllNews();

    List<News> listNewsByUsername(String username);

    List<News> listLatestNews(int num);

    int addNewNews(News record);

    News getNewsById(Long id);

    int editNews(News record);

    int deleteNews(Long id);

}
