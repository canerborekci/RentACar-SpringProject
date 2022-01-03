package com.etiya.rentACarSpring.business.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarImageListDto {
	private int id;

    private int carId;

    private String imagePath;

    private Date imageDate;
}
