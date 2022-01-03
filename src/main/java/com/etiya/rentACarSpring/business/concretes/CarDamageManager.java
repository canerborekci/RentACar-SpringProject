package com.etiya.rentACarSpring.business.concretes;


import com.etiya.rentACarSpring.business.abstracts.CarDamageService;
import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.CarDamageListDto;
import com.etiya.rentACarSpring.business.requests.carDamage.CreateCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.carDamage.DeleteCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.carDamage.UpdateCarDamageRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarDamageDao;
import com.etiya.rentACarSpring.entities.CarDamage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarDamageManager implements CarDamageService {
    private CarDamageDao carDamageDao;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private LanguageWordService languageWordService;

    @Autowired
    public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService, CarService carService, LanguageWordService languageWordService) {
        this.carDamageDao = carDamageDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
        this.languageWordService = languageWordService;
    }

    @Override
    public DataResult<List<CarDamageListDto>> getAll() {
        List<CarDamage> list = this.carDamageDao.findAll();
        List<CarDamageListDto> response = list.stream().map(carDamage -> modelMapperService.forDto().
                map(carDamage, CarDamageListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarDamageListDto>>(response, this.languageWordService.getValueByKey(Messages.DAMAGELIST).getData());
    }

    @Override
    public Result save(CreateCarDamageRequest createCarDamageRequest) {
        Result result = BusinessRules.run(carService.existsByCarId(createCarDamageRequest.getCarId()));
        if (result != null) {
            return result;
        }
        CarDamage carDamage = modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
        this.carDamageDao.save(carDamage);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.DAMAGEADD).getData());
    }

    @Override
    public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {
        Result result = BusinessRules.run(existsByCarDamageId(deleteCarDamageRequest.getCarDamageId()));
        if (result != null) {
            return result;
        }
        CarDamage carDamage = modelMapperService.forRequest().map(deleteCarDamageRequest, CarDamage.class);
        this.carDamageDao.delete(carDamage);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.DAMAGEDELETE).getData());
    }

    @Override
    public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
        Result result = BusinessRules.run(existsByCarDamageId(updateCarDamageRequest.getCarDamageId()),
                carService.existsByCarId(updateCarDamageRequest.getCarId()));
        if (result != null) {
            return result;
        }
        CarDamage carDamage = modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
        this.carDamageDao.save(carDamage);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.DAMAGEUPDATE).getData());
    }

    @Override
    public DataResult<List<CarDamageListDto>> getDamagesByCarId(int carId) {
        List<CarDamage> request = carDamageDao.getByCar_Id(carId);
        List<CarDamageListDto> response = request.stream().map(carDamage -> modelMapperService.forDto().map(carDamage, CarDamageListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarDamageListDto>>(response, this.languageWordService.getValueByKey(Messages.DAMAGEBELONGTOCAR).getData());
    }

    private Result existsByCarDamageId(int carDamageId) {
        boolean result = this.carDamageDao.existsById(carDamageId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.DAMAGENOTFOUND).getData());
        }
        return new SuccessResult();
    }


}
