package com.ajay.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajay.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
