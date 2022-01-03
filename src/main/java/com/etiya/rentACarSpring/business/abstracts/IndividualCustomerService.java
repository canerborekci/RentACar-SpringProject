package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.individualCustomer.CreateIndividualRequest;
import com.etiya.rentACarSpring.business.requests.individualCustomer.DeleteIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;


public interface IndividualCustomerService {
	Result add(CreateIndividualRequest createIndividualRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);
	DataResult<List<IndividualCustomerSearchListDto>> getAll();


}
