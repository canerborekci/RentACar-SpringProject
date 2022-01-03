package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.CityService;
import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.CitySearchListDto;
import com.etiya.rentACarSpring.business.requests.city.CreateCityRequest;
import com.etiya.rentACarSpring.business.requests.city.DeleteCityRequest;
import com.etiya.rentACarSpring.business.requests.city.UpdateCityRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.CityDao;
import com.etiya.rentACarSpring.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {
    private CityDao cityDao;
    private ModelMapperService modelMapperService;
    private LanguageWordService languageWordService;

    @Autowired
    public CityManager(CityDao cityDao, ModelMapperService modelMapperService, LanguageWordService languageWordService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
        this.languageWordService = languageWordService;

    }

    @Override
    public Result save(CreateCityRequest createCityRequest) {
        Result result = BusinessRules.run(existsByCityName(createCityRequest.getCityName()));

        if (result != null) {
            return result;
        }
        City city = modelMapperService.forRequest().map(createCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CITYADD).getData());
    }

    @Override
    public Result update(UpdateCityRequest updateCityRequest) {
        Result result = BusinessRules.run(existsByCityId(updateCityRequest.getCityId()));
        if (result != null) {
            return result;
        }
        City city = modelMapperService.forRequest().map(updateCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CITYUPDATE).getData());
    }

    @Override
    public Result delete(DeleteCityRequest deleteCityRequest) {
        Result result = BusinessRules.run(existsByCityId(deleteCityRequest.getCityId()));
        if (result != null) {
            return result;
        }
        City city = modelMapperService.forRequest().map(deleteCityRequest, City.class);
        this.cityDao.delete(city);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CITYDELETE).getData());
    }

    @Override
    public DataResult<List<CitySearchListDto>> getAll() {
        List<City> result = this.cityDao.findAll();
        List<CitySearchListDto> response = result.stream().map(city -> modelMapperService.forDto()
                .map(city, CitySearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CitySearchListDto>>(response, this.languageWordService.getValueByKey(Messages.CITYLIST).getData());
    }

    @Override
    public Result existsCarByCityId(int cityId) {
        boolean result = this.cityDao.existsById(cityId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CITYNOTFOUND).getData());
        }
        return new SuccessResult();
    }

    @Override
    public Result existsByCityId(int cityId) {
        boolean result = this.cityDao.existsById(cityId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CITYNOTFOUND).getData());
        }
        return new SuccessResult();
    }

    private Result existsByCityName(String cityName) {
        City city = this.cityDao.getByCityName(cityName);
        if (city != null) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CITYALREADYEXISTS).getData());
        }
        return new SuccessResult();
    }
}
