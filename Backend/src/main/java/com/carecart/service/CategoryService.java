package com.carecart.service;

import java.util.List;

import com.carecart.exception.CategoryException;
import com.carecart.models.Category;

public interface CategoryService {
	Category addCategory(Category category) throws CategoryException;
	boolean deleteCategory(long categoryId) throws CategoryException;
	List<Category> showAllCategory()throws CategoryException;
}
