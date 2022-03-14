package com.se1610.dao.implement;

import java.util.List;

import com.se1610.dao.ICategoryDAO;
import com.se1610.mapper.CategoryMapper;
import com.se1610.model.Category;

public class CategoryDAO extends AbstractDAO<Category> implements ICategoryDAO {

   

    @Override
    public List<Category> getAll() {
        String sql = "select * from Category";
        return query(sql, new CategoryMapper());
    }

}
