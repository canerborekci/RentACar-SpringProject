package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.RentalAdditionalListDto;
import com.etiya.rentACarSpring.business.dtos.RentalsAdditionalSearchListDto;
import com.etiya.rentACarSpring.business.requests.rentalsAddiitional.CreateRentalsAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.rentalsAddiitional.DeleteRentalsAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.rentalsAddiitional.UpdateRentalsAdditionalRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.entities.RentalsAdditional;

import java.util.List;

public interface RentalsAdditionalService {
    Result add(CreateRentalsAdditionalRequest createRentalAdditional);
    DataResult<List<RentalsAdditionalSearchListDto>> getAll();
    Result update(UpdateRentalsAdditionalRequest updateRentalsAdditional);
    Result delete(DeleteRentalsAdditionalRequest deleteRentalAdditionalRequest);
    List<RentalsAdditionalSearchListDto> getByRentalId(int rentalId);

}
