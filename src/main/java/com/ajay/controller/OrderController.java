package com.ajay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.model.Order;
import com.ajay.model.User;
import com.ajay.service.OrderService;
import com.ajay.service.UserService;

@RestController
public class OrderController {

	@Autowired
	OrderService service;
	
	@Autowired
	UserService userService;
	
	@GetMapping("admin/orders/all")
	public List<Order> getAllOrders() {
	return service.getAllOrders();	
	}
	
	@GetMapping("admin/orders/{useremail}")
	public List<Order> getOrdersByUser(@PathVariable String useremail){
		//User user = userService.getByUsername(username).get();
		return service.getOrdersByUser(useremail);
	}
	
	@GetMapping("admin/order/{orderid}")
	public Optional<Order> getOrderById(@PathVariable int orderid){
		return service.getOrderById(orderid);
	}
	
	/*
	 * @GetMapping("user/orders") public List<Order> getCurrentUserOrders(){ return
	 * service.getCurrentUserOrders(); }
	 */
	
	@PutMapping("admin/orders/updateStatus/{orderid}")
	public String updateOrderstatus(@PathVariable int orderid, @RequestBody String orderStatus) {
		return service.updateStatusByOrderid(orderid, orderStatus);
	}
	
}
