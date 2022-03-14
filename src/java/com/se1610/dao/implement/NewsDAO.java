package com.se1610.dao.implement;

import com.se1610.model.Category;
import com.se1610.model.News;
import java.util.List;
import com.se1610.dao.INewsDAO;
import com.se1610.mapper.NewMapper;

public class NewsDAO extends AbstractDAO<News> implements INewsDAO {

    @Override
    public List<News> findByCategoryID(int categoryID) {
        String sql = "select * from news\n"
                + "where category_id = ?";
         return query(sql, new NewMapper(), categoryID);
    }

    @Override
    public List<Category> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
