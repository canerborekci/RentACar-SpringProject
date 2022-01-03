package com.etiya.rentACarSpring.business.requests.car;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	@JsonIgnore
	private int id;
	
	@NotNull
    private int colorId;

    @NotNull
    private int brandId;

    @NotNull
    @Min(1980)
    @Max(2022)
    private int modelYear;

    @NotNull
    @Min(100)
    @Max(10000)
    private int dailyPrice;
   
    @Max(1900)
    private int findeksScore;

    @NotNull
    @Size(min = 4,max = 150)
    private String description;

    @NotNull
    private int cityId;
    @NotNull
    private int kilometer;

}
