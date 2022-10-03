package com.ajay.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ajay.model.Role;
import com.ajay.model.User;
import com.ajay.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

	@Autowired
	RoleService roleService;

	@Autowired
	EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void registerUser(User user) {
		/*
		 * Set<Role> roles = new HashSet<Role>();
		 * roles.add(roleService.getRoleById(2).get());
		 */
		String encPwd = passwordEncoder.encode(user.getPassword());
		user.setPassword(encPwd);
		user.getRoles().add(roleService.getRoleById(2).get());
		// user.getRoles().add(roleService.getRoleById(2).get()); // assign 'user' role
		// to new users.

		if (repo.existsByEmail(user.getEmail()) || repo.existsByUsername(user.getUsername())) { // check if email is
																								// already registered
			System.out.println("This Username/Email is already registered.");
		} else {
			repo.save(user);
			emailService.sendRegisterEmail(user.getEmail(), user.getFirstname(), "Ajaydeep1717@gmail.com");
		}
	}

	public List<User> getAllUsers() {
		return repo.findAll();
	}

	public Optional<User> getUserById(int userId) {
		return repo.findById(userId);
	}

	public Optional<User> getByUsername(String username) {
		return repo.findByUsername(username);
	}

	/*
	 * public Optional<User> getByUsernameOrEmail(String usernameOrEmail) { return
	 * repo.findByUsernameOrEmail(usernameOrEmail); }
	 */

	public void deleteUserById(int userId) {
		repo.deleteById(userId);
	}

	public String updateUserById(int userId, User u) {
		User user = repo.findById(userId).get();
		System.out.println(user.toString());
		System.out.println(u.getFirstname());
		try {
			if (u.getFirstname() != null) {
				user.setFirstname(u.getFirstname());
			}
			if (u.getLastname() != null) {
				user.setLastname(u.getLastname());
			}
			if (u.getPassword() != null) {
				String encPwd = passwordEncoder.encode(u.getPassword());
				user.setPassword(encPwd);
			}
			if (u.getContact() != null) {
				user.setContact(u.getContact());
			}
			if (u.getSsn() != null) {
				user.setSsn(u.getSsn());
			}
			repo.save(user);
			return "User details updated successfully.";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return "User details not updated.";
		}
	}
	
	public Optional<User> getCurrentUser() {
		MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> user= Optional.of(myUserDetails.getUser());
		return user;
	}

	/*
	 * public void updateUser(User user) { repo.save(user); }
	 */
}
