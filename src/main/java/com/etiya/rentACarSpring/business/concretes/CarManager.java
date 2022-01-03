package com.etiya.rentACarSpring.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.*;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.CarDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;

import com.etiya.rentACarSpring.business.requests.car.DeleteCarRequest;
import com.etiya.rentACarSpring.business.requests.car.UpdateCarRequest;
import com.etiya.rentACarSpring.business.requests.car.CreateCarRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorDataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarDao;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetailWithColorAndBrand;

@Service
public class CarManager implements CarService {
    private CarDao carDao;
    private ModelMapperService mapperService;
    private BrandService brandService;
    private ColorService colorService;
    private CarImageService carImageService;
    private CityService cityService;
    private LanguageWordService languageWordService;

    @Autowired
    public CarManager(CarDao carDao, ModelMapperService mapperService, BrandService brandService,
                      ColorService colorService, CarImageService carImageService, CityService cityService, LanguageWordService languageWordService) {
        this.carDao = carDao;
        this.mapperService = mapperService;
        this.brandService = brandService;
        this.colorService = colorService;
        this.carImageService = carImageService;
        this.cityService = cityService;
        this.languageWordService = languageWordService;
    }

    public CarManager(CarDao carDao) {
        super();
        this.carDao = carDao;
    }

    @Override
    public DataResult<List<CarSearchListDto>> getAll() {

        List<Car> result = this.carDao.findAll();
        List<CarSearchListDto> response = result.stream()
                .map(car -> mapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult(response, this.languageWordService.getValueByKey(Messages.CARLIST).getData());

    }

    @Override
    public Result save(CreateCarRequest createCarRequest) {
        Result result = BusinessRules.run(colorService.existCarByColorId(createCarRequest.getColorId()),
                brandService.existsCarByBrandId(createCarRequest.getBrandId()),
                cityService.existsCarByCityId(createCarRequest.getCityId()));
        if (result != null) {
            return result;
        }

        Car car = mapperService.forRequest().map(createCarRequest, Car.class);
        this.carDao.save(car);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CARADD).getData());
    }

    @Override
    public Result delete(DeleteCarRequest deleteCarRequest) {
        Result result = BusinessRules.run(existsByCarId(deleteCarRequest.getId()));
        if (result != null) {
            return result;
        }
        Car car = mapperService.forRequest().map(deleteCarRequest, Car.class);
        this.carDao.delete(car);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CARDELETE).getData());

    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) {
        Result result = BusinessRules.run(existsByCarId(updateCarRequest.getId()),
                brandService.existsCarByBrandId(updateCarRequest.getBrandId()),
                colorService.existCarByColorId(updateCarRequest.getColorId()),
                cityService.existsCarByCityId(updateCarRequest.getCityId()));
        if (result != null) {
            return result;
        }
        Car car = mapperService.forRequest().map(updateCarRequest, Car.class);
        this.carDao.save(car);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CARUPDATE).getData());
    }

    @Override
    public DataResult<List<CarDetailWithColorAndBrand>> getCarWithColorAndBrandDetails() {
        List<CarDetailWithColorAndBrand> result = this.carDao.getCarWithColorAndBrandDetails();
        return new SuccessDataResult<List<CarDetailWithColorAndBrand>>(result, this.languageWordService.getValueByKey(Messages.CARBRANDANDCOLORLIST).getData());
    }

    @Override
    public DataResult<List<CarDetailDto>> getCarDetails(int id) {

        List<CarDetail> result = this.carDao.getCarDetails(id);
        if (result.size() == 0) {
            return new ErrorDataResult<List<CarDetailDto>>(null, this.languageWordService.getValueByKey(Messages.CARNOTFOUND).getData());
        }
        List<CarDetailDto> response = result.stream().map(carDetail -> mapperService.forDto().map(carDetail, CarDetailDto.class)).collect(Collectors.toList());
        List<String> imagePaths = new ArrayList<>();
        for (int i = 0; i < this.carImageService.getImageByCarId(id).getData().size(); i++) {
            imagePaths.add(this.carImageService.getImageByCarId(id).getData().get(i).getImagePath());
        }
        response.get(0).setCarImagePaths(imagePaths);

        return new SuccessDataResult<>(response, this.languageWordService.getValueByKey(Messages.CARLIST).getData());
    }

    @Override
    public Result checkCarExists(int carId) {// exists
        Car car = this.carDao.getById(carId);
        if (car == null) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CARNOTFOUND).getData());
        }
        return new SuccessResult();
    }

    @Override
    public DataResult<List<CarSearchListDto>> getByBrandId(int brandId) {
        List<Car> cars = this.carDao.findAllByBrand_BrandId(brandId);
        List<CarSearchListDto> result = cars.stream()
                .map(car -> mapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());
        if (result.size() == 0) {
            return new ErrorDataResult<List<CarSearchListDto>>(null, this.languageWordService.getValueByKey(Messages.CARNOTFOUND).getData());
        }
        return new SuccessDataResult<List<CarSearchListDto>>(result, this.languageWordService.getValueByKey(Messages.CARGETBRAND).getData());
    }

    @Override
    public DataResult<List<CarSearchListDto>> getByColorId(int colorId) {
        List<Car> cars = this.carDao.findAllByColor_ColorId(colorId);
        List<CarSearchListDto> result = cars.stream()
                .map(car -> mapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());
        if (result.size() == 0) {
            return new ErrorDataResult<List<CarSearchListDto>>(null, this.languageWordService.getValueByKey(Messages.CARNOTFOUND).getData());
        }
        return new SuccessDataResult<List<CarSearchListDto>>(result, this.languageWordService.getValueByKey(Messages.CARGETCOLOR).getData());
    }

    @Override
    public DataResult<List<CarSearchListDto>> getByCityId(int cityId) {
        List<Car> cars = this.carDao.findAllByCity_CityId(cityId);
        List<CarSearchListDto> result = cars.stream()
                .map(car -> mapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());
        if (result.size() == 0) {
            return new ErrorDataResult<List<CarSearchListDto>>(null, this.languageWordService.getValueByKey(Messages.CARNOTFOUND).getData());
        }
        return new SuccessDataResult<List<CarSearchListDto>>(result, this.languageWordService.getValueByKey(Messages.CARGETCITY).getData());
    }


    @Override
    public DataResult<CarSearchListDto> getById(int id) {
        Car car = this.carDao.findById(id).get();
        if (car != null) {
            CarSearchListDto carSearchListDto = mapperService.forDto().map(car, CarSearchListDto.class);
            return new SuccessDataResult<CarSearchListDto>(carSearchListDto, this.languageWordService.getValueByKey(Messages.CARFOUND).getData());
        }
        return new ErrorDataResult<CarSearchListDto>(null, this.languageWordService.getValueByKey(Messages.CARNOTFOUND).getData());
    }


    @Override
    public Result updateCityAfterCarDelivered(int carId, int cityId) {
        Car request = this.carDao.getById(carId);
        UpdateCarRequest updateCarRequest = mapperService.forRequest().map(request, UpdateCarRequest.class);
        updateCarRequest.setCityId(cityId);
        Car car = mapperService.forRequest().map(updateCarRequest, Car.class);
        return new SuccessDataResult<>(this.carDao.save(car));
    }

    @Override
    public Result updateKilometerAfterCarDelivered(int carId, int kilometer) {
        Car car = this.carDao.getById(carId);
        car.setKilometer(kilometer);
        this.carDao.save(car);
        return new SuccessResult();
    }


    @Override
    public Result existsByCarId(int id) {
        boolean result = this.carDao.existsById(id);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CARNOTFOUND).getData());
        }
        return new SuccessResult();
    }

    @Override
    public DataResult<List<CarSearchListDto>> getAllWithoutMaintenance() {
        List<CarSearchListDto> result = this.carDao.getCarWithoutCarMaintenance();

        if (result.size() == 0) {
            return new ErrorDataResult<List<CarSearchListDto>>(null, this.languageWordService.getValueByKey(Messages.CARNOTFOUND).getData());
        }
        return new SuccessDataResult<List<CarSearchListDto>>(result);
    }


}
