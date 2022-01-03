package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.CorporateCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.authantication.CorporateCustomerRegisterRequest;
import com.etiya.rentACarSpring.business.requests.corporate.CreateCorporateRequest;
import com.etiya.rentACarSpring.business.requests.corporate.DeleteCorporateRequest;
import com.etiya.rentACarSpring.business.requests.corporate.UpdateCorporateRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;


public interface CorporateCustomerService {
	
	Result add(CreateCorporateRequest createCorporateRequest);
	Result update(UpdateCorporateRequest updateCorporateRequest);
	Result delete(DeleteCorporateRequest deleteCorporateRequest);
	DataResult<List<CorporateCustomerSearchListDto>> getAll();

	

}
