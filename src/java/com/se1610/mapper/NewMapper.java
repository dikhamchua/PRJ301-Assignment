/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1610.mapper;

import com.se1610.model.News;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PHAM KHAC VINH
 */
public class NewMapper implements RowMapper<News> {

    @Override
    public News mapRow(ResultSet resultSet) {
        try {
            News news = new News();
            news.setId(resultSet.getInt("id"));
            news.setTitle(resultSet.getString("title"));
            news.setContent(resultSet.getString("content"));
            news.setCategoryID(resultSet.getInt("category_id"));
            news.setCreateDate(resultSet.getDate("create_date"));
            return news;
        } catch (SQLException ex) {
            return null;
        }
    }

}
