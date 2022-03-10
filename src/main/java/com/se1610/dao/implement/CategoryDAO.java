package com.se1610.dao.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.se1610.dao.ICategoryDAO;
import com.se1610.model.Category;

public class CategoryDAO implements ICategoryDAO {
	
	public Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://VINH\\\\SQLEXPRESS:1433;databaseName=PRJ301_Assignment";
			String username = "sa";
			String password = "123";
			
			return DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} 
		
	}
	
	
	@Override
	public List<Category> getAll() {
		List<Category> result = new ArrayList<>();
		Connection connection = getConnection();
		String sql = "select * from Category";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Category category = new Category();
					category.setId(resultSet.getInt("id"));
					category.setName(resultSet.getString("name"));
					category.setCode(resultSet.getString("code"));
					result.add(category);
				}
				
				
				
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			}finally {
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
		return null;
	}
	
	
}
