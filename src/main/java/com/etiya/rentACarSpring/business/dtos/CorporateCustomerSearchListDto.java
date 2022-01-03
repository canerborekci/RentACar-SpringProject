package com.etiya.rentACarSpring.business.dtos;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CorporateCustomerSearchListDto {
	private int id;
	private String companyName;
	private String taxNumber;
	private int findeksScore;
}
