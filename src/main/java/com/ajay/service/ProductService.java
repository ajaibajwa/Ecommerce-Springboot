package com.ajay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajay.DTO.ProductDTO;
import com.ajay.model.Category;
import com.ajay.model.Product;
import com.ajay.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repo;
	
	@Autowired
	CategoryService categoryService;
	
	public List<Product> getAllProducts(){
		return repo.findAll();
	}
	
	public Optional<Product> getproductById(int productId){
		return repo.findById(productId);
	}
	
	public Product addProduct(ProductDTO productDto) {
		Product product = new Product();
		Optional<Category> category = categoryService.getCategoryById(productDto.getCategoryid());
		product.setCategory(category.get());
		product.setProductname(productDto.getProductname());
		product.setPrice(productDto.getPrice());
		product.setDescription(productDto.getDescription());
		product.setWeight(productDto.getWeight());
		product.setImageuri(productDto.getImageuri());
		product.setStockavailable(productDto.getStockavailable());
		return repo.save(product);
	}
	
	public void deleteProductById(int productId) {
		repo.deleteById(productId);
	}
	
	public void updateProductById(int productId, ProductDTO productDto) {
		Product product = repo.findById(productId).get();
		product.setProductname(productDto.getProductname());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setWeight(productDto.getWeight());
		product.setStockavailable(productDto.getStockavailable());
		product.setImageuri(productDto.getImageuri());
		Optional<Category> category = categoryService.getCategoryById(productDto.getCategoryid());
		product.setCategory(category.get());
		repo.save(product);
	}
	
	public void updateProductStockById(int productId, int stockAvailable) {
		Product product = repo.findById(productId).get();
		product.setStockavailable(stockAvailable);
		repo.save(product);
	}
}
