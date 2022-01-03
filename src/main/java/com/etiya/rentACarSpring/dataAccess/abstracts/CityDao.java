package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.Brand;
import com.etiya.rentACarSpring.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City,Integer> {
    boolean existsById(int cityId);
    City getByCityName(String cityName);
}
