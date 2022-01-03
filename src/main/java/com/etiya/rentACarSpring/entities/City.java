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
@Table(name = "cities")
public class City {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "city_id")
    private int cityId;

    @Column(name = "city_name")
    private  String cityName;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<Car> cars;

    @JsonIgnore
    @OneToMany(mappedBy = "takeCity")
    private List<Rental> takeCityRentals;

    @JsonIgnore
    @OneToMany(mappedBy = "returnCity")
    private List<Rental> returnCityRentals;
}
