package com.clarieSolve.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clarieSolve.entity.User;
import com.clarieSolve.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public String register(User user) {
		
		Optional<User> existingUser=userRepository.findByEmail(user.getEmail());
		if(existingUser.isPresent()) {
			return "Email already exists";
		}
		
		userRepository.save(user);
		return "Registration Successful";
	}

}
