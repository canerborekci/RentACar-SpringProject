package com.etiya.rentACarSpring.business.dtos;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.etiya.rentACarSpring.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardSearchListDto {
	private int cardInformationId;
	
	private String cardName;
	
	private String cardNumber;
	
	private String cardHolderName;
	
	private String expirationDate;
	
	private String cvv;
	
	private int userId;

}
