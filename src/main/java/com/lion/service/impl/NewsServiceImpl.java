package com.lion.service.impl;

import com.lion.dao.ext.NewsDao;
import com.lion.entity.News;
import com.lion.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/31.
 */
@Service("NewsService")
public class NewsServiceImpl implements NewsService{

    @Autowired
    NewsDao newsDao;

    public List<News> listAllNews() {
        return newsDao.selectAllNews();
    }

    public List<News> listNewsByUsername(String username) {
        return newsDao.selectNewsByUsername(username);
    }

    public List<News> listLatestNews(int num) {
        return newsDao.selectLatestNews(num);
    }

    public int addNewNews(News record) {
        return newsDao.insertSelective(record);
    }

    public News getNewsById(Long id) {
        return newsDao.selectByPrimaryKey(id);
    }

    public int editNews(News record) {
        return newsDao.updateByPrimaryKeySelective(record);
    }

    public int deleteNews(Long id) {
        return newsDao.deleteByPrimaryKey(id);
    }
}
