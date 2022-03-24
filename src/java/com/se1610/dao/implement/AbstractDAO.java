/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1610.dao.implement;

import com.se1610.dao.GenericDAO;
import com.se1610.dto.Parameter;
import com.se1610.mapper.RowMapper;
import com.sun.org.apache.bcel.internal.generic.Type;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHAM KHAC VINH
 */
public class AbstractDAO<T> implements GenericDAO<T> {

    protected Connection connection;

    /**
     * it use to get a connection to database
     *
     * @return an connection or null
     */
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

    /**
     * use to select from database
     *
     * @param <T>
     * @param sql
     * @param rowMapper
     * @param parameters
     * @return a list can select
     */
    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Parameter... parameters) {
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

    /**
     * Set parameter to statement
     *
     * @param statement
     * @param parameters
     */
    protected void setParameter(PreparedStatement statement, Parameter... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                int index = i + 1;

                if (parameter.getValue() instanceof Integer) {
                    statement.setInt(index, (int) parameter.getValue());
                } else if (parameter.getValue() instanceof String) {
                    statement.setString(index, (String) parameter.getValue());
                } else if (parameter.getValue() instanceof Long) {
                    statement.setLong(index, (Long) parameter.getValue());
                } else if (parameter.getValue() instanceof Date) {
                    statement.setDate(index, (Date) parameter.getValue());
                } else if (parameter.getValue() instanceof Double) {
                    statement.setDouble(index, (Double) parameter.getValue());
                } else if (parameter.getValue() == null) {
                    statement.setNull(index, parameter.getType());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * use to update or delete
     *
     * @param sql - update or delete query
     * @param parameters - the parameter can be string, integer, ...
     */
    @Override
    public void update(String sql, Parameter... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * use to insert data into database
     *
     * @param sql
     * @param parameters
     * @return 0 or id of that instance
     */
    @Override
    public int insert(String sql, Parameter... parameters) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        int id = 0;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            setParameter(statement, parameters);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            connection.commit();
            return id;

        } catch (SQLException e) {
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
