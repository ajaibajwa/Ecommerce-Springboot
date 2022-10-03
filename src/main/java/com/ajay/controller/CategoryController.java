package com.ajay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.model.Category;
import com.ajay.service.CategoryService;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
//@RequestMapping("/admin")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@GetMapping("/categories")
	public List<Category> listAllCategories(){
		return service.getAllCategories();
	}
	
	@GetMapping("/categories/{categoryId}")
	public Optional<Category> getCategoryById(@PathVariable Integer categoryId){
		return service.getCategoryById(categoryId);
	}
	
	@PostMapping("/categories")
	public void addCategory(@RequestBody Category category) {
		service.addCategory(category);
		//return "Category added.";
	}
	
	@DeleteMapping("categories/delete/{categoryId}")
	public void deleteCategoryById(@PathVariable Integer categoryId) {
		service.deleteCategoryById(categoryId);
	}
	
	@PutMapping("categories/update/{categoryId}")
	public void updateCategoryById(@PathVariable Integer categoryId, @RequestBody Category cat) {
		service.updateCategoryById(categoryId, cat);
		//return "Category deleted.";
	}
}
