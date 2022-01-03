package com.etiya.rentACarSpring.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "rent_date")
	private LocalDate rentDate;

	@Column(name = "return_date")
	private LocalDate returnDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;

	@OneToOne(mappedBy = "rental")
	private Invoice invoice;

	@ManyToOne
	@JoinColumn(name = "take_city_id")
	private City takeCity;

	@ManyToOne
	@JoinColumn(name = "return_city_id")
	private City returnCity;

	@Column(name ="start_kilometer")
	private int startKilometer;
	@Column(name ="return_kilometer")
	private int returnKilometer;

	@OneToMany(mappedBy = "rental")
	private List<RentalsAdditional> rentalsAdditionals;

}
