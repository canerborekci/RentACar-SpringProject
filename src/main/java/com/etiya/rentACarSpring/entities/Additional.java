package com.etiya.rentACarSpring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "additionals")
public class Additional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "additional_id")
    private int additionalId;
    @Column(name = "additional_name")
    private String additionalName;
    @Column(name = "daily_price")
    private int dailyPrice;

    @OneToMany(mappedBy = "additional")
    private List<RentalsAdditional> rentalsAdditionals;
}
