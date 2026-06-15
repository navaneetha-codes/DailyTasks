package com.clarieSolve.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
	@SequenceGenerator(
	    name = "USERS_SEQ",
	    sequenceName = "USERS_SEQ",
	    allocationSize = 1
	)

	private Long id;
	@NotBlank(message="Name is required")
	@Pattern(
		    regexp = "^[A-Za-z ]+$",
		    message = "Name should contain only letters and spaces"
		)
	private String name;
	@Email(message="Invalid email")
	@NotBlank(message="Email is required")
	@Column(unique=true)
	@Pattern(
		    regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",
		    message = "Email must be a valid gmail.com address"
		)
	private String email;
	
	@NotBlank(message="Password is required")
	private String password;
	@NotBlank(message="Role is required")
	@Pattern(
		    regexp = "^[A-Za-z ]+$",
		    message = "Role should contain only letters and spaces"
		)
    private String role;

}
