package com.se1610.dao;

import com.se1610.model.Category;
import com.se1610.model.News;
import java.util.List;

public interface INewsDAO extends GenericDAO<News> {
    //find a news by ID
    News findOne(int id);
    
    //find all news
    List<Category> findAll();

    //find a list news by categoryID
    List<News> findByCategoryID(int categoryID);

    //save news to database
    int save(News news);
    
    void update(News news);
    
    void delete(int id);
}
