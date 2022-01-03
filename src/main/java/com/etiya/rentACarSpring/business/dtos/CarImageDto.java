package com.etiya.rentACarSpring.business.dtos;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarImageDto {
	private String ImagePath;
	private Date date;

}
