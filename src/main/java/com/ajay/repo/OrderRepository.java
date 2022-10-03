package com.ajay.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajay.model.Order;
import com.ajay.model.User;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	List<Order> findByUseremail(String useremail);

}
