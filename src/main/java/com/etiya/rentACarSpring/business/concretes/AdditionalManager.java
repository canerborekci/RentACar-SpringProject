package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.AdditionalService;
import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.RentalAdditionalListDto;
import com.etiya.rentACarSpring.business.requests.Additional.CreateRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.Additional.DeleteRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.Additional.UpdateRentalAdditionalRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.AdditionalDao;
import com.etiya.rentACarSpring.entities.Additional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalManager implements AdditionalService {
    private AdditionalDao rentalAdditionalDao;
    private ModelMapperService modelMapperService;
    private LanguageWordService languageWordService;

    @Autowired
    public AdditionalManager(AdditionalDao rentalAdditionalDao, ModelMapperService modelMapperService, LanguageWordService languageWordService) {
        this.rentalAdditionalDao = rentalAdditionalDao;
        this.modelMapperService = modelMapperService;
        this.languageWordService = languageWordService;
    }

    @Override
    public DataResult<List<RentalAdditionalListDto>> getAll() {
        List<Additional> result = this.rentalAdditionalDao.findAll();
        List<RentalAdditionalListDto> response = result.stream().map(additionalService -> modelMapperService.forDto()
                .map(additionalService, RentalAdditionalListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult(response, this.languageWordService.getValueByKey(Messages.ADDITIONALSERVICELIST).getData());
    }

    @Override
    public DataResult<Additional> getById(int rentalAdditionalId) {
        return new SuccessDataResult<Additional>(this.rentalAdditionalDao.getById(rentalAdditionalId));
    }

    @Override
    public Result save(CreateRentalAdditionalRequest createRentalAdditionalRequest) {
        Result result = BusinessRules.run(existsByAdditionalName(createRentalAdditionalRequest.getAdditionalName()));
        if (result != null) {
            return result;
        }
        Additional rentalAdditional = this.modelMapperService.forRequest().map(createRentalAdditionalRequest, Additional.class);
        this.rentalAdditionalDao.save(rentalAdditional);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.ADDITIONALSERVICEADD).getData());
    }

    @Override
    public Result update(UpdateRentalAdditionalRequest updateRentalAdditionalRequest) {
        Result result = BusinessRules.run(existsByRentalAdditionalId(updateRentalAdditionalRequest.getAdditionalId()),
                existsByAdditionalName(updateRentalAdditionalRequest.getAdditionalName())
        );
        if (result != null) {
            return result;
        }
        Additional rentalAdditional = this.modelMapperService.forRequest().map(updateRentalAdditionalRequest, Additional.class);
        this.rentalAdditionalDao.save(rentalAdditional);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.ADDITIONALSERVICEUPDATE).getData());
    }

    @Override
    public Result delete(DeleteRentalAdditionalRequest deleteRentalAdditionalRequest) {
        Result result = BusinessRules.run(existsByRentalAdditionalId(deleteRentalAdditionalRequest.getAdditionalId()));
        if (result != null) {
            return result;
        }
        Additional rentalAdditional = this.modelMapperService.forRequest().map(deleteRentalAdditionalRequest, Additional.class);
        this.rentalAdditionalDao.delete(rentalAdditional);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.ADDITIONALSERVICEDELETE).getData());
    }

    @Override
    public Result existsByRentalAdditionalId(int additionalId) {
        boolean result = this.rentalAdditionalDao.existsById(additionalId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.ADDITIONALSERVICENOTFOUND).getData());
        }
        return new SuccessResult();
    }

    private Result existsByAdditionalName(String additionalName) {
        Additional additional = this.rentalAdditionalDao.getByAdditionalName(additionalName);
        if (additional != null) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.ADDITIONALCANNOTREPEAT).getData());
        }
        return new SuccessResult();
    }
}
