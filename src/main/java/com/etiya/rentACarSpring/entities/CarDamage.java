package com.etiya.rentACarSpring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_damages")
public class CarDamage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_damage_id")
    private int carDamageId;

    @Column(name = "damage_information")
    private String damageInformation;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car car;
}
