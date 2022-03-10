package com.se1610.service.implement;

import java.util.List;

import javax.inject.Inject;

import com.se1610.dao.ICategoryDAO;
import com.se1610.model.Category;
import com.se1610.service.ICategoryService;

public class CategoryService implements ICategoryService{
	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return categoryDAO.getAll();
	}

}
