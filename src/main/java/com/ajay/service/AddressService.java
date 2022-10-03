package com.ajay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ajay.model.Address;
import com.ajay.model.User;
import com.ajay.repo.AddressRepository;
import com.ajay.repo.UserRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository repo;
	
	@Autowired
	UserService userService;
	public List<Address> getAllAddresses(){
		return repo.findAll();
	}
	
	public List<Address> getAddressesByUsername(String username){
		User user = userService.getByUsername(username).get();
		return repo.findByUser(user);
	}
	
	public void addAddress(Address address) {
		//address.setUser(userService.getByUsername("ajay").get());
		//System.out.println(userService.getByUsername("ajay").get());
		address.setUser(userService.getCurrentUser().get());
		repo.save(address);
	}
	
	public Optional<Address> getAddressById(Integer addressid) {
		return repo.findById(addressid);
	}
	
	public void deleteAddressById(Integer addressid) {
		repo.deleteById(addressid);
	}
}
