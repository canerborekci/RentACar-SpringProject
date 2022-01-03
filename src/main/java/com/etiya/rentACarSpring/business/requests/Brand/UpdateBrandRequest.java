package com.etiya.rentACarSpring.business.requests.Brand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
	private int brandId;
	@NotBlank
	@NotNull
	private String brandName;

}
