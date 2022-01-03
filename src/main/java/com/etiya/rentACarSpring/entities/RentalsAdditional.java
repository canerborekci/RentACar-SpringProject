package com.etiya.rentACarSpring.entities;

import com.etiya.rentACarSpring.entities.Additional;
import com.etiya.rentACarSpring.entities.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals_additional")
public class RentalsAdditional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "additional_id")
    private Additional additional;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @Column(name = "additional_fee")
    private int additionalPrice;


}
