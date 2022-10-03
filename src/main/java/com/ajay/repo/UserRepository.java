package com.ajay.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ajay.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
	//Optional<User> findByUsernameOrEmail(String usernameOrEmail);
	Optional<User> findByUsername(String username);
	
	/*
	 * @Query("SELECT u FROM User u WHERE u.username = '?1'") public User
	 * findByUserName(String username);
	 */
}
