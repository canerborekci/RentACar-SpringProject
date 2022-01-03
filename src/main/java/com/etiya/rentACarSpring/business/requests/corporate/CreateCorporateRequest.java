package com.etiya.rentACarSpring.business.requests.corporate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateRequest {
		
	@JsonIgnore
	private int id;
	
	@NotNull
	private String companyName;
	
	@NotNull
	private String taxNumber;
	
	@JsonIgnore
	private int findeksScore;
	
	@Email
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	private String password;
	
}
