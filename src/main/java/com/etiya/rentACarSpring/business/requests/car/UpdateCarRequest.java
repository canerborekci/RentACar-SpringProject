package com.etiya.rentACarSpring.business.requests.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	@NotNull
    private int id;
	@NotNull
	private int colorId;
    @NotNull
	private int brandId;
    @NotNull
	private int modelYear;
	@NotNull
	private int dailyPrice;
	@NotNull
	private String description;
	@NotNull
	private int cityId;
	@NotNull
	private int findeksScore;
	@NotNull
	private int kilometer;



}
