package com.etiya.rentACarSpring.business.dtos;

import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceDto {

	private int carMaintenanceId;

	private String description;

	private String maintenanceDate;

	private String returnDate;

	private int carId;

}
