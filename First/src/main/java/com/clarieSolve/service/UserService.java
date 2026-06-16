package com.clarieSolve.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clarieSolve.dto.LoginRequest;
import com.clarieSolve.entity.User;
import com.clarieSolve.repository.UserRepository;

import jakarta.validation.Valid;

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
	
	public String login(LoginRequest loginRequest) {
		Optional<User> existingUser=userRepository.findByEmail(loginRequest.getEmail());
		if(existingUser.isPresent()) {
			
			User dbUser=existingUser.get();
			if(dbUser.getPassword().equals(loginRequest.getPassword())) {
			
			return "Login Success";
			}else {
				return "Invalid Password";
			}
			
		}
		return "User Not Found";
	}

}
