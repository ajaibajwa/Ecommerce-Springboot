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

import com.ajay.model.Role;
import com.ajay.service.RoleService;

@RestController
public class RoleController {

	@Autowired
	private RoleService service;
	
	@GetMapping("/roles")
	public List<Role> listAllRoles(){
		return service.getAllRoles();
	}
	
	@GetMapping("/roles/{roleId}")
	public Optional<Role> getRoleById(@PathVariable Integer roleId){
		return service.getRoleById(roleId);
	}
	
	@PostMapping("/roles")
	public String addRole(@RequestBody Role role) {
		service.addRole(role);
		return "Role added successfully.";
	}
	
	@DeleteMapping("roles/{roleId}")
	public String deleteRoleById(@PathVariable Integer roleId) {
		service.deleteRoleById(roleId);
		return "Role deleted.";
	}
}
