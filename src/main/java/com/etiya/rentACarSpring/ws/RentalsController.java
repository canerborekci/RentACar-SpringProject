package com.etiya.rentACarSpring.ws;

import java.util.List;

import javax.validation.Valid;

import com.etiya.rentACarSpring.entities.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.RentalService;
import com.etiya.rentACarSpring.business.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.business.requests.rental.CreateRentalRequest;
import com.etiya.rentACarSpring.business.requests.rental.DeleteRentalRequest;
import com.etiya.rentACarSpring.business.requests.rental.UpdateIfCarReturned;
import com.etiya.rentACarSpring.business.requests.rental.UpdateRentalRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

@RestController
@RequestMapping("api/rentals")
public class RentalsController {
	private RentalService rentalService;

	@Autowired
	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
		Result result = this.rentalService.add(createRentalRequest);
		return result;
	}

	@GetMapping("all")
	public DataResult<List<RentalSearchListDto>> getAll() {
		return this.rentalService.getAll();

	}


	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.update(updateRentalRequest);
	}

	@PutMapping("updateIfCarReturned")
	public Result updateIfCarReturned(@RequestBody @Valid UpdateIfCarReturned UpdateIfCarReturned) {
		return this.rentalService.updateIfCarReturned(UpdateIfCarReturned);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteRentalRequest deleteRentalRequest) {
		return this.rentalService.delete(deleteRentalRequest);
	}

}
