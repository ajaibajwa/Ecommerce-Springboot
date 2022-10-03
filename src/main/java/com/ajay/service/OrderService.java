package com.ajay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ajay.model.Order;
import com.ajay.model.User;
import com.ajay.repo.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository repo;
	
	@Autowired
	UserService userService;
	
	public List<Order> getAllOrders(){
		return repo.findAll();
	}
	public Optional<Order> getOrderById(int orderid){
		return repo.findById(orderid);
	}
	
	/*
	 * public List<Order> getCurrentUserOrders(){
	 * 
	 * System.out.println("current user for viewing orders is : "+userService.
	 * getCurrentUser().get().getFirstname()); return
	 * repo.findByUser(userService.getCurrentUser().get()); }
	 */
	
	public List<Order> getOrdersByUser(String userEmail){
		return repo.findByUseremail(userEmail);
	}
	
	public String updateStatusByOrderid(int orderid, String orderStatus) {
		Order order = repo.findById(orderid).get();
		order.setStatus(orderStatus);
		repo.save(order);
		return "Order status changed to "+orderStatus;
		
	}
	
}
