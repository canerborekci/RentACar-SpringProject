package com.etiya.rentACarSpring.business.requests.rental;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.etiya.rentACarSpring.business.dtos.CardInformationDto;
import com.etiya.rentACarSpring.entities.City;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIfCarReturned {
	@NotNull
	private int id;
	@JsonIgnore
	private int carId;
	@JsonIgnore
	private int userId;

	@NotNull
	@ApiModelProperty(example = "2022-01-01")
	private LocalDate returnDate;

	@NotNull
	private int returnCityId;
	@NotNull
	private  int returnKilometer;
	@NotNull
	@Valid
	private CardInformationDto cardInformationDto;
}
