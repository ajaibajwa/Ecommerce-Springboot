package com.ajay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajay.model.Category;
import com.ajay.repo.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository repo;
	
	public void addCategory(Category category) {
		repo.save(category);
	}
	
	public List<Category> getAllCategories(){
		return repo.findAll();
	}
	
	public Optional<Category> getCategoryById(int categoryId){
		return repo.findById(categoryId);
	}
	
	public void deleteCategoryById(int categoryId) {
		repo.deleteById(categoryId);
	}
	
	public void updateCategoryById(int categoryId, Category cat) {
		Category category = repo.findById(categoryId).get();
		category.setCategoryname(cat.getCategoryname());
		repo.save(category);
	}
}
