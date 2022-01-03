package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.RentalAdditionalListDto;
import com.etiya.rentACarSpring.business.requests.Additional.CreateRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.Additional.DeleteRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.Additional.UpdateRentalAdditionalRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.entities.Additional;

import java.util.List;

public interface AdditionalService {
    DataResult<List<RentalAdditionalListDto>> getAll();
    Result save(CreateRentalAdditionalRequest createRentalAdditionalRequest);
    Result update(UpdateRentalAdditionalRequest updateRentalAdditionalRequest);
    Result delete(DeleteRentalAdditionalRequest deleteRentalAdditionalRequest);
    DataResult<Additional> getById(int rentalAdditionalId);
    Result existsByRentalAdditionalId(int additionalId);

}
