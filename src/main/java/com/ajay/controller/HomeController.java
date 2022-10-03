package com.ajay.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.model.User;
import com.ajay.service.MyUserDetails;
import com.ajay.service.UserService;

@RestController
public class HomeController {

	/*
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * 
	 * UserDetails userDetails = (UserDetails) auth.getPrincipal();
	 */

	@GetMapping("/")
	public String home() {
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(user.getUser().getUserid());
		return "<h1>Welcome</h2>";
	}

	@GetMapping("/admin/home")
	public String admin() {
		return "<h1>Welcome admin</h2>";
	}

	@GetMapping("/home")
	public String user() {
		return "<h1>Welcome user</h2>";
	}
}
