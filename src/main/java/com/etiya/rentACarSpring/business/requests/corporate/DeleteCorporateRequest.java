package com.etiya.rentACarSpring.business.requests.corporate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DeleteCorporateRequest {
	@NotNull
	private int id;
	
}
