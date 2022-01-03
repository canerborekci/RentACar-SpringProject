package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.AdditionalService;
import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.abstracts.RentalService;
import com.etiya.rentACarSpring.business.abstracts.RentalsAdditionalService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.RentalAdditionalListDto;
import com.etiya.rentACarSpring.business.dtos.RentalsAdditionalSearchListDto;
import com.etiya.rentACarSpring.business.requests.rentalsAddiitional.CreateRentalsAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.rentalsAddiitional.DeleteRentalsAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.rentalsAddiitional.UpdateRentalsAdditionalRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.RentalsAdditionalDao;
import com.etiya.rentACarSpring.entities.Additional;
import com.etiya.rentACarSpring.entities.Color;
import com.etiya.rentACarSpring.entities.RentalsAdditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalsAdditionalManager implements RentalsAdditionalService {
    private RentalsAdditionalDao rentalsAdditionalDao;
    private ModelMapperService modelMapperService;
    private AdditionalService additionalService;
    private LanguageWordService languageWordService;
    private RentalService rentalService;

    @Autowired
    public RentalsAdditionalManager(RentalsAdditionalDao rentalsAdditionalDao, ModelMapperService modelMapperService, AdditionalService additionalService, LanguageWordService languageWordService, @Lazy RentalService rentalService) {
        this.rentalsAdditionalDao = rentalsAdditionalDao;
        this.modelMapperService = modelMapperService;
        this.additionalService = additionalService;
        this.languageWordService = languageWordService;
        this.rentalService = rentalService;
    }


    @Override
    public Result add(CreateRentalsAdditionalRequest createRentalAdditional) {
        Result result = BusinessRules.run(rentalService.existByRentalId(createRentalAdditional.getRentalId()), additionalService.existsByRentalAdditionalId(createRentalAdditional.getAdditionalId()));
        if (result != null) {
            return result;
        }
        RentalsAdditional rentalsAdditional = modelMapperService.forRequest().map(createRentalAdditional, RentalsAdditional.class);
        rentalsAdditional.setAdditionalPrice(this.additionalService.getById(createRentalAdditional.getAdditionalId()).getData().getDailyPrice());
        this.rentalsAdditionalDao.save(rentalsAdditional);

        return new SuccessResult(this.languageWordService.getValueByKey(Messages.ADDITIONALRENTALITEMADD).getData());
    }


    @Override
    public DataResult<List<RentalsAdditionalSearchListDto>> getAll() {

        List<RentalsAdditional> result = this.rentalsAdditionalDao.findAll();
        List<RentalAdditionalListDto> response = result.stream().map(additionalService -> modelMapperService.forDto().map(additionalService, RentalAdditionalListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult(response, this.languageWordService.getValueByKey(Messages.ADDITIONALRENTALITEMLIST).getData());
    }


    @Override
    public Result update(UpdateRentalsAdditionalRequest updateRentalsAdditional) {
        Result result = BusinessRules.run(existsById(updateRentalsAdditional.getId()), rentalService.existByRentalId(updateRentalsAdditional.getRentalId()), additionalService.existsByRentalAdditionalId(updateRentalsAdditional.getAdditionalId()));
        if (result != null) {
            return result;
        }
        RentalsAdditional rentalsAdditional = this.modelMapperService.forRequest().map(updateRentalsAdditional, RentalsAdditional.class);
        this.rentalsAdditionalDao.save(rentalsAdditional);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.ADDITIONALRENTALITEMUPDATE).getData());
    }


    @Override
    public Result delete(DeleteRentalsAdditionalRequest deleteRentalAdditionalRequest) {
        Result result = BusinessRules.run(existsById(deleteRentalAdditionalRequest.getId()));
        if (result != null) {
            return result;
        }
        RentalsAdditional rentalsAdditional = this.modelMapperService.forRequest().map(deleteRentalAdditionalRequest, RentalsAdditional.class);
        this.rentalsAdditionalDao.delete(rentalsAdditional);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.ADDITIONALRENTALITEMDELETE).getData());
    }

    @Override
    public List<RentalsAdditionalSearchListDto> getByRentalId(int rentalId) {

        List<RentalsAdditional> rentalsAdditionals = this.rentalsAdditionalDao.findByRental_Id(rentalId);
        List<RentalsAdditionalSearchListDto> response = rentalsAdditionals.stream().map(additionalService -> modelMapperService.forDto().map(additionalService, RentalsAdditionalSearchListDto.class)).collect(Collectors.toList());
        return response;
    }

    private Result existsById(int id) {
        boolean result = this.rentalsAdditionalDao.existsById(id);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.ADDITIONALRENTALITEMNOTFOUND).getData());
        }
        return new SuccessResult();
    }


}

