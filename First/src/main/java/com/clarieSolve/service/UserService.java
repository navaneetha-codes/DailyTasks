package com.clarieSolve.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clarieSolve.config.SecurityConfig;
import com.clarieSolve.dto.LoginRequest;
import com.clarieSolve.dto.RegisterUserRequest;
import com.clarieSolve.entity.User;
import com.clarieSolve.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	SecurityConfig securityConfig;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public String register(RegisterUserRequest request) {
		
		Optional<User> existingUser=userRepository.findByEmail(request.getEmail());
		if(existingUser.isPresent()) {
			return "Email already exists";
		}
		String password=request.getPassword();
	
		String encodedPassword =passwordEncoder.encode(password);
		System.out.println("Original Password = " + password);
		System.out.println("Encoded Password = " + encodedPassword);

		request.setPassword(encodedPassword);
		User user = new User();

	    user.setName(request.getName());
	    user.setEmail(request.getEmail());
	    user.setPassword(encodedPassword);
	    user.setRole(request.getRole());

	    userRepository.save(user);

		return "Registration Successful";
	}
	
	public String login(LoginRequest loginRequest) {
		Optional<User> existingUser=userRepository.findByEmail(loginRequest.getEmail());
		if(existingUser.isPresent()) {
			
			User dbUser=existingUser.get();
			
			if (passwordEncoder.matches(loginRequest.getPassword(),dbUser.getPassword())) {
			    return "Login Success";
			}else {
				return "Invalid Password";
			}
			
		}
		return "User Not Found";
	}

}
