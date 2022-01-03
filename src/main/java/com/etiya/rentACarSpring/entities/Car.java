package com.etiya.rentACarSpring.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.etiya.rentACarSpring.entities.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "modelYear")
    private int modelYear;

    @Column(name = "dailyPrice")
    private int dailyPrice;

    @Column(name = "description")
    private String description;
    
    @Column(name= "findeks_score")
    private int findeksScore;

    @Column(name="kilometer")
    private int kilometer;
    
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
    
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    
    
    @OneToMany(mappedBy = "car")
    private List<CarImage> carImages;
    
    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;

    @OneToMany(mappedBy = "car")
    private List<CarMaintenance> carMaintenances;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "car")
    private List<CarDamage> carDamages;

}
