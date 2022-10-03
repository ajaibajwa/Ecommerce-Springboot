package com.ajay.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajay.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
