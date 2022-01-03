package com.etiya.rentACarSpring.business.requests.creditCard;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCreditCardRequest {
	@NotNull
	private int cardInformationId;
	
	@NotBlank
	@NotNull
	private String cardName;

	@NotBlank
	@NotNull
	private String cardNumber;
	
	@NotBlank
	@NotNull
	private String cardHolderName;
	
	@NotBlank
	@NotNull
	private String expirationDate;
	
	@NotBlank
	@NotNull
	private String cvv;

	@NotNull
	private int userId;

}
