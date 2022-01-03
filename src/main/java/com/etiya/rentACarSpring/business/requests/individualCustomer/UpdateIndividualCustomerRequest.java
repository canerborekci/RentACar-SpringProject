package com.etiya.rentACarSpring.business.requests.individualCustomer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateIndividualCustomerRequest {
	@NotNull
	private int id;
	
	@NotNull
	@Size(max = 25)
	private String firstName;
	
	@NotNull
	@Size(max = 25)
	private String lastName;
	
    @NotNull
	@ApiModelProperty(example = "1985-01-01")
	private String birthDay;
	
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Size(min = 8,max = 20)
	private String password;
	
	@JsonIgnore
	private int findeksScore;

}
