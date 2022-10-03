package com.ajay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.DTO.CartDTO;
import com.ajay.DTO.OrderDTO;
import com.ajay.model.Cart;
import com.ajay.service.CartService;


@CrossOrigin(origins = "http://localhost:4201")
@RestController
public class CartController {

	@Autowired
	CartService service;
	
	@GetMapping("admin/cart/all")
	public List<Cart> getAllCarts(){
		return service.getAllCarts();
	}
	
	@GetMapping("user/cart/{userEmail}")
	public List<Cart> getCartByUser(@PathVariable String userEmail){
		return service.getCartByUser(userEmail);
	}
	
	@PostMapping("user/addToCart/{userEmail}")
	public String addToCart(@RequestBody CartDTO cartDTO, @PathVariable String userEmail) {
		return service.addToCart(cartDTO, userEmail);
	}
	
	@PutMapping("user/cart/update/{userEmail}")
	public String updateQuantity(@RequestBody CartDTO cartDto, @PathVariable String userEmail) {
		return service.updateQuantity(cartDto, userEmail);
	}
	
	@PostMapping("user/cart/checkout/{userEmail}")
	public String checkout(@RequestBody OrderDTO orderDTO, @PathVariable String userEmail) {
		return service.Checkout(orderDTO, userEmail);
	}
}
