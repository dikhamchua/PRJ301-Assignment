package com.se1610.service.implement;

import com.se1610.model.News;
import com.se1610.service.INewService;
import java.util.List;
import javax.inject.Inject;
import com.se1610.dao.INewsDAO;

public class NewsService implements INewService{
    @Inject 
    private INewsDAO newsDao;
    
    @Override
    public List<News> findByCategoryID(int categoryID) {
        return newsDao.findByCategoryID(categoryID);
    }

    @Override
    public News save(News news) {
        int newsID = newsDao.save(news);
        return newsDao.findOne(newsID);
    }

}
