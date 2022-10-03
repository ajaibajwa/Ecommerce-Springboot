package com.ajay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.DTO.ProductDTO;
import com.ajay.model.Product;
import com.ajay.service.ProductService;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@GetMapping("/products")
	public List<Product> listAllProducts() {
		return service.getAllProducts();
	}
	
	@GetMapping("/products/{productId}")
	public Optional<Product> getProductById(@PathVariable int productId){
		return service.getproductById(productId);
	}
	
	//@PostMapping("admin/products")
	@PostMapping("/products")
	public void addProduct(@RequestBody ProductDTO productDto) {
		Product product = service.addProduct(productDto);
		//return new ResponseEntity<>(product,HttpStatus.CREATED);
		//return ""+productDto.getProductname()+" product added successfully";
	}
	
	//@DeleteMapping("admin/products/{productId}")
	@DeleteMapping("/products/delete/{productId}")
	public void deleteProductById(@PathVariable Integer productId) {
		service.deleteProductById(productId);
		//return "Product deleted.";
	}
	
	//@PutMapping("admin/products/{productId}")
	@PutMapping("/products/update/{productId}")
	public void updateProductById(@PathVariable Integer productId, @RequestBody ProductDTO productDto) {
		service.updateProductById(productId, productDto);
		//return "Product updated.";
	}
}
