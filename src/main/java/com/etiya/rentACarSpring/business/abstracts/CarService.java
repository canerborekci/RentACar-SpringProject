package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;


import com.etiya.rentACarSpring.business.dtos.CarDetailDto;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;

import com.etiya.rentACarSpring.business.requests.car.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.car.UpdateCarRequest;
import com.etiya.rentACarSpring.business.requests.car.CreateCarRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetailWithColorAndBrand;

public interface CarService {
	DataResult<List<CarSearchListDto>> getAll();
	Result save(CreateCarRequest createCarRequest);
	Result delete(DeleteCarRequest deleteCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	DataResult<List<CarSearchListDto>> getByBrandId(int brandId);
	DataResult<List<CarSearchListDto>> getByColorId(int colorId);
	DataResult<List<CarSearchListDto>> getByCityId(int colorId);
	DataResult<CarSearchListDto> getById(int carId);
	Result updateCityAfterCarDelivered(int carId,int cityId);
	Result updateKilometerAfterCarDelivered(int carId,int kilometer);

	Result existsByCarId(int id);

	//Result existCarDamageByCarId(int id);
	Result checkCarExists(int carId);
	DataResult<List<CarDetailWithColorAndBrand>> getCarWithColorAndBrandDetails();
	DataResult<List<CarDetailDto>> getCarDetails(int carId);
	DataResult<List<CarSearchListDto>> getAllWithoutMaintenance();
	
}
