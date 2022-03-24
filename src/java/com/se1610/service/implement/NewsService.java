package com.se1610.service.implement;

import com.se1610.model.News;
import com.se1610.service.INewService;
import java.util.List;
import javax.inject.Inject;
import com.se1610.dao.INewsDAO;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewsService implements INewService{
    @Inject 
    private INewsDAO newsDao;
    
    @Override
    public List<News> findByCategoryID(int categoryID) {
        return newsDao.findByCategoryID(categoryID);
    }

    @Override
    public News save(News news) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//        java.sql.Date currentDate = new Date(System.currentTimeMillis());
//        String currentDateString = dateFormat.format(currentDate);
//        Date date = dateFormat.parse(currentDateString);
        
        //java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        news.setCreateDate(new Date(System.currentTimeMillis()));
        news.setCreateBy("empty");
        int newsID = newsDao.save(news);
        return newsDao.findOne(newsID);
    }

    @Override
    public News update(News updateNews) {
        News oldNews = newsDao.findOne(updateNews.getId());
        updateNews.setCreateDate(oldNews.getCreateDate());
        updateNews.setCreateBy(oldNews.getCreateBy());
        updateNews.setModifiedDate(new Date(System.currentTimeMillis()));
        updateNews.setModifiedBy("empty nguoi sua");
        
        newsDao.update(updateNews);
        return newsDao.findOne(updateNews.getId());
    }

    @Override
    public void delete(int[] ids) {
//        for (int id : ids) {
//            newsDao.delete(id);
//        }
        for (int i = 0; i < ids.length; i++) {
            newsDao.delete(ids[i]);
        }
    }

}
