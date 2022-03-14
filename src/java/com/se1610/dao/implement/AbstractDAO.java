/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1610.dao.implement;

import com.se1610.dao.GenericDAO;
import com.se1610.mapper.RowMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PHAM KHAC VINH
 */
public class AbstractDAO<T> implements GenericDAO<T> {

    protected Connection connection;

    public Connection getConnection() {
        try {
            String user = "sa";
            String pass = "123";
            String url = "jdbc:sqlserver://VINH\\SQLEXPRESS:1433;databaseName=PRJ301_Assignment";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
            return connection;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            //set parameter 
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                return null;
            }
        }
    }

    protected void setParameter(PreparedStatement statement, Object... parameters) {
        try {
            for (int i = 1; i <= parameters.length; i++) {
                Object parameter = parameters[i - 1];
                if (parameter instanceof Integer) {
                    statement.setInt(i, (int) parameter);
                }else if (parameter instanceof String) {
                    statement.setString(i, (String) parameter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
