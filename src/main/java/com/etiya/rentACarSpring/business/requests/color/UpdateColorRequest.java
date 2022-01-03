package com.etiya.rentACarSpring.business.requests.color;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
	private int colorId;
	@NotNull
	@NotBlank
	private String colorName;

}
