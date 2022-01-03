package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.CitySearchListDto;
import com.etiya.rentACarSpring.business.requests.city.CreateCityRequest;
import com.etiya.rentACarSpring.business.requests.city.DeleteCityRequest;
import com.etiya.rentACarSpring.business.requests.city.UpdateCityRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

import java.util.List;

public interface CityService {
    Result save(CreateCityRequest createCityRequest);
    Result update(UpdateCityRequest updateCityRequest);
    Result delete(DeleteCityRequest deleteCityRequest);
    DataResult<List<CitySearchListDto>> getAll();
    Result existsCarByCityId(int cityId);
    Result existsByCityId(int cityId);
}
