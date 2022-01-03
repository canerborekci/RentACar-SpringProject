package com.etiya.rentACarSpring.ws;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.IndividualCustomerService;
import com.etiya.rentACarSpring.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.individualCustomer.CreateIndividualRequest;
import com.etiya.rentACarSpring.business.requests.individualCustomer.DeleteIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessResult;

@RestController
@RequestMapping("api/customers")
public class IndividualCustomersController {
	private IndividualCustomerService customerService;

	@Autowired
	public IndividualCustomersController(IndividualCustomerService customerService) {
		super();
		this.customerService = customerService;
	}

//	@PostMapping("add")
//	public Result add(@RequestBody @Valid CreateIndividualRequest createIndividualRequest) {
//		this.customerService.add(createIndividualRequest);
//		return new SuccessResult();
//	}

	@GetMapping("all")
	public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
		return this.customerService.getAll();

	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {

		return this.customerService.update(updateIndividualCustomerRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		return this.customerService.delete(deleteIndividualCustomerRequest);
	}
	
	

}
