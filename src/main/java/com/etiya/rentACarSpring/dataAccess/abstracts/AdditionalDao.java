package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.Additional;
import com.etiya.rentACarSpring.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalDao extends JpaRepository<Additional,Integer> {
    boolean existsById(int additionalId);
    Additional getByAdditionalName(String additionalName);

}
