package com.clarieSolve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clarieSolve.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	Optional<User> findByEmail(String email);

}
