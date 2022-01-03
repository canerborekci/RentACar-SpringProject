package com.etiya.rentACarSpring.business.abstracts;


import com.etiya.rentACarSpring.business.dtos.CarDamageListDto;
import com.etiya.rentACarSpring.business.requests.carDamage.CreateCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.carDamage.DeleteCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.carDamage.UpdateCarDamageRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

import java.util.List;

public interface CarDamageService {
    DataResult<List<CarDamageListDto>> getAll();
    Result save(CreateCarDamageRequest createCarDamageRequest);
    Result delete(DeleteCarDamageRequest deleteCarDamageRequest);
    Result update(UpdateCarDamageRequest updateCarDamageRequest);
    DataResult<List<CarDamageListDto>> getDamagesByCarId(int carId);

}
