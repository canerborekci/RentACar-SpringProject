package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.*;
import com.etiya.rentACarSpring.business.requests.authantication.CorporateCustomerRegisterRequest;
import com.etiya.rentACarSpring.business.requests.corporate.CreateCorporateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.requests.individualCustomer.CreateIndividualRequest;
import com.etiya.rentACarSpring.business.requests.authantication.IndividualCustomerRegisterRequest;
import com.etiya.rentACarSpring.business.requests.authantication.UserLoginRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessResult;

@Service
public class AuthManager implements AuthService {
    private UserService userService;
    private IndividualCustomerService individualCustomerService;
    private ModelMapperService modelMapperService;
    private CorporateCustomerService corporateCustomerService;
    private LanguageWordService languageWordService;

    @Autowired
    public AuthManager(UserService userService, IndividualCustomerService individualCustomerService,
                       ModelMapperService modelMapperService, CorporateCustomerService corporateCustomerService, LanguageWordService languageWordService) {
        this.userService = userService;
        this.individualCustomerService = individualCustomerService;
        this.modelMapperService = modelMapperService;
        this.corporateCustomerService = corporateCustomerService;
        this.languageWordService = languageWordService;

    }

    @Override
    public Result individualCustomerRegister(IndividualCustomerRegisterRequest individualCustomerRegisterRequest) {

        CreateIndividualRequest createIndividualRequest = modelMapperService.forRequest()
                .map(individualCustomerRegisterRequest, CreateIndividualRequest.class);

        this.individualCustomerService.add(createIndividualRequest);

        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CUSTOMERADD).getData());
    }

    @Override
    public Result CorporateCustomerRegister(CorporateCustomerRegisterRequest corporateCustomerRegisterRequest) {

        CreateCorporateRequest createCorporateRequest = modelMapperService.forRequest().map(corporateCustomerRegisterRequest, CreateCorporateRequest.class);

        this.corporateCustomerService.add(createCorporateRequest);

        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CUSTOMERADD).getData());
    }

    @Override
    public Result userLogin(UserLoginRequest userLoginRequest) {
        Result result = BusinessRules.run(checkUserEmail(userLoginRequest), checkUserPassword(userLoginRequest));
        if (result != null) {
            return result;
        }

        return new SuccessResult(this.languageWordService.getValueByKey(Messages.LOGINSUCCESS).getData());
    }

    private Result checkUserEmail(UserLoginRequest userLoginRequest) {
        if (this.userService.existsByEmail(userLoginRequest.getEmail()).isSuccess()) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.LOGINERROR).getData());

        }
        return new SuccessResult();
    }

    private Result checkUserPassword(UserLoginRequest userLoginRequest) {
        if (checkUserEmail(userLoginRequest).isSuccess()) {
            if (!this.userService.getByEmail(userLoginRequest.getEmail()).getData().get(0).getPassword().equals(userLoginRequest.getPassword())) {
                return new ErrorResult(this.languageWordService.getValueByKey(Messages.LOGINERROR).getData());
            }
        }
        return new SuccessResult();
    }

}
