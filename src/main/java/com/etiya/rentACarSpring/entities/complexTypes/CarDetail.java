package com.etiya.rentACarSpring.entities.complexTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetail {
	private int carId;
	private String colorName;
	private String brandName;
	private int dailyPrice;
	private int modelYear;
	private String description;
}
