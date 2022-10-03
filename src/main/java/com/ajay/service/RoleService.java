package com.ajay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajay.model.Category;
import com.ajay.model.Role;
import com.ajay.repo.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository repo;
	
	public void addRole(Role role) {
		repo.save(role);
	}
	
	public List<Role> getAllRoles(){
		return repo.findAll();
	}
	
	public Optional<Role> getRoleById(int roleId){
		return repo.findById(roleId);
	}
	
	public void deleteRoleById(int roleId) {
		repo.deleteById(roleId);
	}
}
