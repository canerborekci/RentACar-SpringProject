package com.etiya.rentACarSpring.business.requests.corporate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateRequest {
	
	@NotNull
	private int id;
	
	@NotNull
	private String companyName;
	
	@NotNull
	private String taxNumber;

	@NotNull
	@Size(min = 8,max = 20)
	private String password;

	@JsonIgnore
	private int findeksScore;

	@Email
	@NotNull
	@NotBlank
	private String email;
	
}
