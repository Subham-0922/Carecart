package com.carecart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carecart.exception.CategoryException;
import com.carecart.models.Category;
import com.carecart.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public Category addCategory(Category category) throws CategoryException {
		// TODO Auto-generated method stub
		if(category==null)throw new CategoryException("Category Data is Null");
		
		return categoryRepository.save(category);
	}

	@Override
	public boolean deleteCategory(long categoryId) throws CategoryException {
		// TODO Auto-generated method stub
		Category category=categoryRepository.findById(categoryId).orElseThrow(()->new CategoryException("Category with this id not found"));
		categoryRepository.delete(category);
		return true;
	}

	@Override
	public List<Category> showAllCategory() throws CategoryException {
		// TODO Auto-generated method stub
		List<Category> categories=categoryRepository.findAll();
		if(categories.isEmpty())throw new CategoryException("Category list is empty");
		return categories;
	}
	
}
