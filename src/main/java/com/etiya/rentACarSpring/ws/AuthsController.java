package com.etiya.rentACarSpring.ws;

import javax.validation.Valid;

import com.etiya.rentACarSpring.business.requests.authantication.CorporateCustomerRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.AuthService;
import com.etiya.rentACarSpring.business.requests.authantication.IndividualCustomerRegisterRequest;
import com.etiya.rentACarSpring.business.requests.authantication.UserLoginRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

@RestController
@RequestMapping("api/authentications")
public class AuthsController {
	private AuthService authService;

	@Autowired
	public AuthsController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("individualCustomerRegister")
	Result individualCustomerRegister(
			@RequestBody @Valid IndividualCustomerRegisterRequest individualCustomerRegisterRequest) {
		return this.authService.individualCustomerRegister(individualCustomerRegisterRequest);
	}

	@PostMapping("CorporateCustomerRegister")
	Result corporateCustomerRegister(
			@RequestBody @Valid CorporateCustomerRegisterRequest corporateCustomerRegisterRequest) {
		return this.authService.CorporateCustomerRegister(corporateCustomerRegisterRequest);
	}

	@PostMapping("login")
	Result login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
		return this.authService.userLogin(userLoginRequest);
	}

}
