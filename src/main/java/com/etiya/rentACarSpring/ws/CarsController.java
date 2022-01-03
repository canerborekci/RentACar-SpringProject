package com.etiya.rentACarSpring.ws;

import java.util.List;

import javax.validation.Valid;

import com.etiya.rentACarSpring.business.dtos.CarDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.requests.car.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.car.UpdateCarRequest;
import com.etiya.rentACarSpring.business.requests.car.CreateCarRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetailWithColorAndBrand;

@RestController
@RequestMapping("api/cars")
public class CarsController {
	private CarService carService;
	@Autowired
	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}
	
	@GetMapping("all")
	public DataResult<List<CarSearchListDto>>  getAll(){	
		return carService.getAll();
	}
	
	@GetMapping("getAllWithoutMaintenances")
    public DataResult<List<CarSearchListDto>>  getAllWithoutMaintenances(){
        return carService.getAllWithoutMaintenance();
	}
	
	@GetMapping("getAllByColorId")
	public DataResult<List<CarSearchListDto>>  getAllByColorId(int colorId){
		
		return carService.getByColorId(colorId);
	}
	@GetMapping("getAllByCityId")
	public DataResult<List<CarSearchListDto>>  getAllByCityId(int cityId){

		return carService.getByCityId(cityId);
	}
	@GetMapping("getAllByBrandId")
	public DataResult<List<CarSearchListDto>>  getAllByBrandId(int brandId){
		
		return carService.getByBrandId(brandId);
	}
	@GetMapping("getCarWithColorAndBrandDetails")
	public DataResult<List<CarDetailWithColorAndBrand>>  getCarWithColorAndBrandDetails(){
		return carService.getCarWithColorAndBrandDetails();
	}
	
	@GetMapping("getCarDetails")
	public DataResult<List<CarDetailDto>>  getCarDetails(int carId){
		return carService.getCarDetails(carId);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		return this.carService.save(createCarRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}
	
	
	

}
