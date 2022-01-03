package com.etiya.rentACarSpring.entities.complexTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailWithColorAndBrand {
	private int carId;
	private String brandName;
	private String colorName;
	private int dailyPrice;
	private int modelYear;
	private String description;

}
