package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.business.requests.rental.CreateRentalRequest;
import com.etiya.rentACarSpring.business.requests.rental.DeleteRentalRequest;
import com.etiya.rentACarSpring.business.requests.rental.UpdateIfCarReturned;
import com.etiya.rentACarSpring.business.requests.rental.UpdateRentalRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.entities.Rental;

public interface RentalService {
	Result add(CreateRentalRequest createRentalRequest);
	Result delete(DeleteRentalRequest deleteRentalRequest);
	Result update(UpdateRentalRequest updateRentalRequest);
	Result updateIfCarReturned(UpdateIfCarReturned updateIfCarReturned);
	DataResult<List<RentalSearchListDto>> getAll();
	Result checkIfCarReturned(int carId);
	DataResult<Rental> getById(int id);
	DataResult<Integer> getCountOfRentalDays(int id);
	//DataResult<Integer> getRentalAdditionalDailyPrice(int rentalAdditionalId);
	//DataResult<Integer> getRentalDailyTotalPrice(CreateRentalRequest createRentalRequest);
	Result checkUserHasNoRental(int userId);
	Result checkRentalIsCompleted (int rentalId);

	Result existByRentalId(int id);
	
}