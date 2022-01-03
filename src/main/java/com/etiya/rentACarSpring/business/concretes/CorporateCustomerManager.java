package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.CreditCardService;
import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.abstracts.RentalService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.requests.authantication.CorporateCustomerRegisterRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.CorporateCustomerService;
import com.etiya.rentACarSpring.business.dtos.CorporateCustomerSearchListDto;
import com.etiya.rentACarSpring.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACarSpring.business.requests.corporate.CreateCorporateRequest;
import com.etiya.rentACarSpring.business.requests.corporate.DeleteCorporateRequest;
import com.etiya.rentACarSpring.business.requests.corporate.UpdateCorporateRequest;
import com.etiya.rentACarSpring.core.utilities.adapters.FindeksScoreCreator;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.CorporateCustomerDao;
import com.etiya.rentACarSpring.entities.CorporateCustomer;
import com.etiya.rentACarSpring.entities.IndividualCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {
    private CorporateCustomerDao corporateCustomerDao;
    private ModelMapperService modelMapperService;
    private FindeksScoreCreator findeksScoreCreator;
    private LanguageWordService languageWordService;


    @Autowired
    public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService,
                                    FindeksScoreCreator findeksScoreCreator, LanguageWordService languageWordService) {
        super();
        this.corporateCustomerDao = corporateCustomerDao;
        this.modelMapperService = modelMapperService;
        this.findeksScoreCreator = findeksScoreCreator;
        this.languageWordService = languageWordService;

    }

    @Override
    public Result add(CreateCorporateRequest createCorporateRequest) {
        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateRequest, CorporateCustomer.class);
        corporateCustomer.setFindeksScore(findeksScoreCreator.FindeksScoreCreator());
        corporateCustomerDao.save(corporateCustomer);

        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CUSTOMERADD).getData());
    }

    @Override
    public Result update(UpdateCorporateRequest updateCorporateRequest) {
        Result result = BusinessRules.run(existsByUserId(updateCorporateRequest.getId()));

        if (result != null) {
            return result;
        }

        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(updateCorporateRequest, CorporateCustomer.class);
        corporateCustomer.setFindeksScore(findeksScoreCreator.FindeksScoreCreator());
        this.corporateCustomerDao.save(corporateCustomer);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CUSTOMERUPDATE).getData());
    }

    @Override
    public Result delete(DeleteCorporateRequest deleteCorporateRequest) {
        Result result = BusinessRules.run(existsByUserId(deleteCorporateRequest.getId()));

        if (result != null) {
            return result;
        }
        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(deleteCorporateRequest, CorporateCustomer.class);
        this.corporateCustomerDao.delete(corporateCustomer);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CUSTOMERDELETE).getData());
    }

    @Override
    public DataResult<List<CorporateCustomerSearchListDto>> getAll() {
        List<CorporateCustomer> result = this.corporateCustomerDao.findAll();
        List<CorporateCustomerSearchListDto> response = result.stream().map(corporateCustomer -> modelMapperService.forDto()
                .map(corporateCustomer, CorporateCustomerSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CorporateCustomerSearchListDto>>(response, this.languageWordService.getValueByKey(Messages.CUSTOMERLIST).getData());
    }


    private Result existsByUserId(int userId) {
        boolean result = this.corporateCustomerDao.existsById(userId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CUSTOMERNOTFOUND).getData());

        }
        return new SuccessResult();
    }

}
