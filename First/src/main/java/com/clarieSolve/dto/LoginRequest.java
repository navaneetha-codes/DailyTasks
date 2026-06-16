package com.clarieSolve.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequest {
	
	@NotBlank(message="Email is required")
	private String email;
	@NotBlank(message="Password is required")
	@Pattern(
		    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,12}$",
		    message = "Password must be 8-12 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character"
		)
	private String password;

}
