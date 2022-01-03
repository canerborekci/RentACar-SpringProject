package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.requests.authantication.CorporateCustomerRegisterRequest;
import com.etiya.rentACarSpring.business.requests.authantication.IndividualCustomerRegisterRequest;
import com.etiya.rentACarSpring.business.requests.authantication.UserLoginRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

public interface AuthService {
	Result individualCustomerRegister(IndividualCustomerRegisterRequest individualCustomerRegisterRequest);

    Result CorporateCustomerRegister(CorporateCustomerRegisterRequest corporateCustomerRegisterRequest);

    Result userLogin(UserLoginRequest userLoginRequest);
}
