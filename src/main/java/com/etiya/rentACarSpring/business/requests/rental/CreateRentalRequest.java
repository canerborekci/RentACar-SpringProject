package com.etiya.rentACarSpring.business.requests.rental;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	@JsonIgnore
	private int rentalId;
	
	@NotNull
	@ApiModelProperty(example = "2022-01-01")
	private LocalDate rentDate;

	@JsonIgnore
	private LocalDate returnDate;
	
	@NotNull
	private int userId;
	
	@NotNull
	private int carId;

	@NotNull
	private int takeCityId;

	@NotNull
	private int startKilometer;
	@JsonIgnore
	private int additionalFee;




}
