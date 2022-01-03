package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.RentalsAdditional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalsAdditionalDao extends JpaRepository<RentalsAdditional,Integer> {
    List<RentalsAdditional> findByRental_Id(int rentalId);
    Boolean existsById(int id);
}
