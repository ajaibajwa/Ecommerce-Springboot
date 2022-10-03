package com.ajay.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajay.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByRolename(String rolename);
}
