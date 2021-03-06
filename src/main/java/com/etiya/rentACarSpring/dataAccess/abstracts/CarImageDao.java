package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.CarImage;
import com.etiya.rentACarSpring.entities.Rental;

public interface CarImageDao extends JpaRepository<CarImage, Integer>{
	int countCarImageByCar_Id(int carId);
	
	List<CarImage> findAllByCarId(int id);

	boolean existsById(int carImageId);

	boolean existsByCar_Id(int id);
	
	//Boolean existByCar_CarId(int carId);
	
}
