package com.etiya.rentACarSpring.dataAccess.abstracts;


import java.util.List;

import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.entities.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer>{
	Rental getById(int id);
	Rental getByCarId(int id);
	List<Rental> findAllRentalByCarId(int id);
	@Query(value="select (return_date)-(rent_date) as datedifference from rentals where id= ?1 ",nativeQuery = true)
	Integer getCountofRentalDays (int id);
	int countRentalByUser_Id(int user_id);

}
