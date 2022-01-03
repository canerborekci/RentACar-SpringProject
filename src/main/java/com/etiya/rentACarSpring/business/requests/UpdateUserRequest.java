package com.etiya.rentACarSpring.business.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
	@NotNull
	private int userId;
	
	@NotNull
	private String password;
		
	@Email
	@NotNull
	private String email;

}
