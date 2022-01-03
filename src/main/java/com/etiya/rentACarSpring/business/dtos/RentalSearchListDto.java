package com.etiya.rentACarSpring.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalSearchListDto {
	private int id;
	private int userId;
	private int carId;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private int takeCityId;
	private int returnCityId;

}
