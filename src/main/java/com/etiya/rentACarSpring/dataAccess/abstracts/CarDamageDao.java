package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.CarDamage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarDamageDao extends JpaRepository<CarDamage,Integer> {
    List<CarDamage> getByCar_Id(int carId);
    boolean existsById(int carDamageId);
    boolean existsByCar_Id(int id);

}
