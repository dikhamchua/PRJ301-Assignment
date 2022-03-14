package com.se1610.dao;

import java.util.List;

import com.se1610.model.Category;

public interface ICategoryDAO extends GenericDAO<Category>{
	List<Category> getAll();
}
