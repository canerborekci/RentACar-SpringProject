package com.etiya.rentACarSpring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit_card_information")
@Entity
public class CreditCardInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_information_id")
	private int cardInformationId;
	@Column(name = "card_name")
	private String cardName;
	@Column(name = "card_number")
	private String cardNumber;
	@Column(name = "card_holder_name")
	private String cardHolderName;
	@Column(name = "expiration_date")
	private String expirationDate;
	@Column(name = "cvv")
	private String cvv;
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
}
