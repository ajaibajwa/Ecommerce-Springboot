package com.ajay.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajay.model.Address;
import com.ajay.model.User;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByUser(User user);
}
