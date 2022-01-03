package com.etiya.rentACarSpring.business.concretes;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.CreditCardService;
import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.abstracts.RentalService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.IndividualCustomerService;
import com.etiya.rentACarSpring.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.individualCustomer.CreateIndividualRequest;
import com.etiya.rentACarSpring.business.requests.individualCustomer.DeleteIndividualCustomerRequest;
import com.etiya.rentACarSpring.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.etiya.rentACarSpring.core.utilities.adapters.FindeksScoreCreator;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.rentACarSpring.entities.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
    private IndividualCustomerDao customerDao;
    private ModelMapperService mapperService;
    private FindeksScoreCreator findeksScoreCreator;
    private LanguageWordService languageWordService;


    @Autowired
    public IndividualCustomerManager(IndividualCustomerDao customerDao, ModelMapperService mapperService, FindeksScoreCreator findeksScoreCreator, LanguageWordService languageWordService) {
        this.customerDao = customerDao;
        this.mapperService = mapperService;
        this.findeksScoreCreator = findeksScoreCreator;
        this.languageWordService = languageWordService;
    }

    @Override
    public Result add(CreateIndividualRequest createIndividualRequest) {
        IndividualCustomer individualCustomer = mapperService.forRequest().map(createIndividualRequest, IndividualCustomer.class);
        individualCustomer.setFindeksScore(findeksScoreCreator.FindeksScoreCreator());
        customerDao.save(individualCustomer);


        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CUSTOMERADD).getData());
    }

    @Override
    public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        Result result = BusinessRules.run(existsByUserId(updateIndividualCustomerRequest.getId()));

        if (result != null) {
            return result;
        }
        IndividualCustomer individualCustomer = mapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
        individualCustomer.setFindeksScore(findeksScoreCreator.FindeksScoreCreator());
        this.customerDao.save(individualCustomer);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CUSTOMERUPDATE).getData());
    }

    @Override
    public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
        Result result = BusinessRules.run(existsByUserId(deleteIndividualCustomerRequest.getId()));

        if (result != null) {
            return result;
        }
        IndividualCustomer individualCustomer = mapperService.forRequest().map(deleteIndividualCustomerRequest, IndividualCustomer.class);
        this.customerDao.delete(individualCustomer);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CUSTOMERDELETE).getData());
    }

    @Override
    public DataResult<List<IndividualCustomerSearchListDto>> getAll() {

        List<IndividualCustomer> individualCustomers = this.customerDao.findAll();
        List<IndividualCustomerSearchListDto> response = individualCustomers.stream().map(individualCustomer -> mapperService.forDto().map(individualCustomer, IndividualCustomerSearchListDto.class)).collect(Collectors.toList());
        if (response.size() == 0) {
            return new ErrorDataResult<List<IndividualCustomerSearchListDto>>(null, this.languageWordService.getValueByKey(Messages.CUSTOMERNOTFOUND).getData());
        }
        return new SuccessDataResult<List<IndividualCustomerSearchListDto>>(response, this.languageWordService.getValueByKey(Messages.CUSTOMERLIST).getData());
    }


    private Result existsByUserId(int userId) {
        boolean result = this.customerDao.existsById(userId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CUSTOMERNOTFOUND).getData());
        }
        return new SuccessResult();
    }


}
