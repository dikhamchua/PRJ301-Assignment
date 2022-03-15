package com.se1610.dao.implement;

import com.se1610.model.Category;
import com.se1610.model.News;
import java.util.List;
import com.se1610.dao.INewsDAO;
import com.se1610.mapper.NewMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @Override
    public int save(News news) {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        int id = 0;
        try {
            String sql = "insert into [news](title, content,category_id)\n"
                    + "values(? ,? ,?)";
            connection = getConnection();
            
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setInt(3, news.getCategoryID());
            
             
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            connection.commit();
            return id;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
            }
        }
        return 0;
    }

}
