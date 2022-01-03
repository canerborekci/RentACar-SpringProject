package com.etiya.rentACarSpring.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerSearchListDto {
	private int individualId;
	private String firstName;
	private String lastName;
	private int findeksScore;
	private String birthday;
	private int userId;

}
