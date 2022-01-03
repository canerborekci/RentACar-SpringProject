package com.etiya.rentACarSpring.business.requests.rental;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalRequest {
	@NotNull
	private int id;
	
	@NotNull
	@ApiModelProperty(example = "2022-01-01")
	private LocalDate rentDate;

	@ApiModelProperty(example = "2022-01-01")
	private LocalDate returnDate;
	
	@NotNull
	private int userId;
	
	@NotNull
	private int carId;
	@NotNull
	private int takeCityId;

	private int returnCityId;
	@NotNull
	private int startKilometer;

	private  int returnKilometer;
	
	

}
