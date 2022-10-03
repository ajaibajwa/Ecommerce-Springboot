package com.ajay.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajay.model.Cart;
import com.ajay.model.Product;
import com.ajay.model.User;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	//List<Cart> findByUser(User user);
	List<Cart> findByUseremail(String useremail);
	Cart findByProductAndUseremail(Product product, String useremail);
}
