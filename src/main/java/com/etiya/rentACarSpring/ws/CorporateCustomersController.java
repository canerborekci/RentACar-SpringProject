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

import com.etiya.rentACarSpring.business.abstracts.CorporateCustomerService;
import com.etiya.rentACarSpring.business.dtos.CorporateCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.corporate.CreateCorporateRequest;
import com.etiya.rentACarSpring.business.requests.corporate.DeleteCorporateRequest;
import com.etiya.rentACarSpring.business.requests.corporate.UpdateCorporateRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

@RestController
@RequestMapping("api/corporateCustomers")
public class CorporateCustomersController {
	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		super();
		this.corporateCustomerService = corporateCustomerService;
	}
//	@PostMapping("add")
//	public Result add(@RequestBody @Valid CreateCorporateRequest createCorporateRequest) {
//		return this.corporateCustomerService.add(createCorporateRequest);
//	}
	@GetMapping("all")
	public DataResult<List<CorporateCustomerSearchListDto>> getAll(){
		return this.corporateCustomerService.getAll();
		
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCorporateRequest updateCorporateRequest) {
		return this.corporateCustomerService.update(updateCorporateRequest);
		
	}
	
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCorporateRequest deleteCorporateRequest) {
		
		return this.corporateCustomerService.delete(deleteCorporateRequest);
	}
}
