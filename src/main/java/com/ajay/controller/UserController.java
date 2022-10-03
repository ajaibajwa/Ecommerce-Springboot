package com.ajay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.model.User;
import com.ajay.service.MyUserDetails;
import com.ajay.service.RoleService;
import com.ajay.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("admin/users")
	public List<User> listAllUsers() {
		return service.getAllUsers();
	}

	@GetMapping("admin/users/{userId}")
	public Optional<User> getUserById(@PathVariable Integer userId) {
		Optional<User> user = service.getUserById(userId);
		return user;
	}
	
	@GetMapping("/user/details")
	public Optional<User> getCurrentUser() {
	
		return service.getCurrentUser();
	}
	
	@GetMapping("admin/users/{username}")
	public Optional<User> getByUserName(@PathVariable String username) {
		Optional<User> user = service.getByUsername(username);
		return user;
	}
	
	/*
	 * @GetMapping("/users/{usernameOrEmail}") public Optional<User>
	 * getByUsernameOrEmail(@PathVariable String usernameOrEmail) { Optional<User>
	 * user = service.getByUsernameOrEmail(usernameOrEmail); return user; }
	 */

	@PostMapping("user/register")
	public String registerUser(@RequestBody User user) {
		service.registerUser(user);
		return "<h1>Hi "+user.getFirstname()+", You have successfully registered</h1>";
	}

	@DeleteMapping("admin/users/{userId}")
	public String deleteUserById(@PathVariable Integer userId) {
		service.deleteUserById(userId);
		return "User deleted.";
	}

	@PutMapping("admin/users/{userId}")
	public String updateUserById(@PathVariable Integer userId, @RequestBody User user) {
		service.updateUserById(userId, user);
		return "User Updated successfully";
	}
	
	@PutMapping("/user/update")
	public String updateCurrentUser(@RequestBody User user) {
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int currentUserId = userDetails.getUser().getUserid();
		System.out.println("current user id :"+currentUserId);
		return service.updateUserById(currentUserId, user);
		//return "User details updated successsfully.";
	}
}
