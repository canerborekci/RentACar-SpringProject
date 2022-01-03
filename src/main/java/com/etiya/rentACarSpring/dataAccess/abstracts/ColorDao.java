package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.Color;

public interface ColorDao extends JpaRepository<Color, Integer>{
	
	boolean existsById(int colorId);
	Color getByColorName(String colorName);

}
