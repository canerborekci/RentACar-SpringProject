package com.etiya.rentACarSpring.business.requests.individualCustomer;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.etiya.rentACarSpring.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualRequest {
	@JsonIgnore
	private int id;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
    @NotNull
	@ApiModelProperty(example = "1985-01-01")
	private LocalDate birthDay;
	
	
	@NotNull
	@Email
	@ApiModelProperty(example = "ad.soyad@etiya.com")
	private String email;
	
	@NotNull
	private String password;
	
	@JsonIgnore
	private int findeksScore;
}
