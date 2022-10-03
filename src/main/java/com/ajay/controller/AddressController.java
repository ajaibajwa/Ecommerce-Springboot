package com.ajay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.model.Address;
import com.ajay.model.User;
import com.ajay.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	AddressService service;
	
	@GetMapping("admin/address/all")
	public List<Address> listAllAddresses(){
		return service.getAllAddresses();
	}
	
	@GetMapping("admin/address/id/{addressid}")
	public Optional<Address> getAddressById(@PathVariable int addressid){
		return service.getAddressById(addressid);
	}
	@GetMapping("admin/address/name/{username}")
	public List<Address> getAddressesByUsername(@PathVariable String username){
		return service.getAddressesByUsername(username);
	}
	@PostMapping("user/address")
	public String addAddress(@RequestBody Address address) {
		 service.addAddress(address);
		 return "Addess added.";
	}
	@DeleteMapping("admin/address/{addressid}")
	public String deleteAddressById(@PathVariable Integer addressid) {
		service.deleteAddressById(addressid);
		return "Address deleted";
	}
}
