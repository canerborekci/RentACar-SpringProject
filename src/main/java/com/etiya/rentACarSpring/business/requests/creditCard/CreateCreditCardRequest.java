package com.etiya.rentACarSpring.business.requests.creditCard;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.etiya.rentACarSpring.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {
	@JsonIgnore
	private int cardInformationId;
	@NotNull
	private String cardName;
	@NotNull
	private String cardNumber;
	@NotNull
	private String cardHolderName;
	@NotNull
	private String expirationDate;
	@NotNull
	private String cvv;
	@NotNull
	private int userId;
}
