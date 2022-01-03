package com.etiya.rentACarSpring.business.requests;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
	@JsonIgnore
	private int userId;
	

		
	@Email
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	

}
