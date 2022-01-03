package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetailWithColorAndBrand;

public interface CarDao extends JpaRepository<Car, Integer> {

	List<Car> findAllByColor_ColorId(int id);

	List<Car> findAllByBrand_BrandId(int id);

	List<Car> findAllByCity_CityId(int id);

	Car getById(int id);

	boolean existsById(int id);

	@Query("Select new com.etiya.rentACarSpring.entities.complexTypes.CarDetailWithColorAndBrand "
			+ " (c.id,cl.colorName,b.brandName,c.dailyPrice,c.modelYear,c.description) "
			+ "From Car c Inner join c.color cl inner join c.brand b")
	List<CarDetailWithColorAndBrand> getCarWithColorAndBrandDetails();


	@Query("Select new com.etiya.rentACarSpring.entities.complexTypes.CarDetail "
			+ " (c.id,cl.colorName,b.brandName,c.dailyPrice,c.modelYear,c.description) "
			+ "From Car c Inner join c.color cl inner join c.brand b  where c.id = ?1 ")
	List<CarDetail> getCarDetails(int id);



	@Query("Select new com.etiya.rentACarSpring.business.dtos.CarSearchListDto"
			+ "(c.id,c.dailyPrice,c.description,c.findeksScore,c.modelYear,c.brand.brandId,c.color.colorId) "
			+ "From Car c Left Join  c.carMaintenances cm WHERE " +
			"(cm.maintenanceDate is not null AND cm.returnDate is not null) or (cm.maintenanceDate is null AND cm.returnDate is null)" +
			" group by  c.id")
	List<CarSearchListDto> getCarWithoutCarMaintenance();



}
