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

import com.etiya.rentACarSpring.business.abstracts.CreditCardService;
import com.etiya.rentACarSpring.business.dtos.CreditCardSearchListDto;
import com.etiya.rentACarSpring.business.requests.creditCard.CreateCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.creditCard.DeleteCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.creditCard.UpdateCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.entities.CreditCardInformation;

@RestController
@RequestMapping("api/creditCards")
public class CreditCardsController {
	private CreditCardService creditCardService;
	
	@Autowired
	public CreditCardsController(CreditCardService creditCardService) {
		super();
		this.creditCardService = creditCardService;
	}
	@GetMapping("/getAll")
	public DataResult<List<CreditCardSearchListDto>> getAll() {
		return this.creditCardService.getAll();
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCreditCardRequest createCardRequest) {
		return this.creditCardService.add(createCardRequest);
	}
	@PutMapping("/update")
	public Result update(@Valid @RequestBody UpdateCreditCardRequest updateCreditCardRequest) {
		return this.creditCardService.update(updateCreditCardRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@Valid DeleteCreditCardRequest deleteCreditCardRequest) {
		return this.creditCardService.delete(deleteCreditCardRequest);
	}
}
