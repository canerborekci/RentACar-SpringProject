package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.CreditCardSearchListDto;
import com.etiya.rentACarSpring.business.requests.creditCard.CreateCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.creditCard.DeleteCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.creditCard.UpdateCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.entities.CreditCardInformation;

public interface CreditCardService {
	Result add(CreateCreditCardRequest createCreditCardRequest);
	Result update(UpdateCreditCardRequest updateCreditCardRequest);
	Result delete(DeleteCreditCardRequest deleteCreditCardRequest);
	DataResult<CreditCardInformation> getById(int cardInformationId);
	DataResult<List<CreditCardSearchListDto>> getAll();

	
}
