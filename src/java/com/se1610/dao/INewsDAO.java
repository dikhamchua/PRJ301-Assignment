package com.se1610.dao;

import com.se1610.model.Category;
import com.se1610.model.News;
import java.util.List;

public interface INewsDAO extends GenericDAO<News>{
    List<Category> findAll();
    List<News> findByCategoryID(int categoryID);
}
