package com.etiya.rentACarSpring.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.CarMaintenanceService;
import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.abstracts.RentalService;
import com.etiya.rentACarSpring.business.dtos.CarMaintenanceDto;
import com.etiya.rentACarSpring.business.requests.maintenance.CreateCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.maintenance.DeleteCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.maintenance.UpdateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarMaintenanceDao;
import com.etiya.rentACarSpring.entities.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {
    private CarMaintenanceDao carMaintenanceDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    private CarService carService;
    private LanguageWordService languageWordService;


    @Autowired
    public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService, RentalService rentalService, CarService carService, LanguageWordService languageWordService) {
        super();
        this.carMaintenanceDao = carMaintenanceDao;
        this.modelMapperService = modelMapperService;
        this.rentalService = rentalService;
        this.carService = carService;
        this.languageWordService = languageWordService;
    }

    @Override
    public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
        Result result = BusinessRules.run(checkIfCarReturnedFromMaintenance(createCarMaintenanceRequest.getCarId()),
                maintenanceDateAndExpectedReturnDateComparison(createCarMaintenanceRequest.getMaintenanceDate(), createCarMaintenanceRequest.getExpectedReturnDate()),
                rentalService.checkIfCarReturned(createCarMaintenanceRequest.getCarId()),
                carService.existsByCarId(createCarMaintenanceRequest.getCarId()));

        if (result != null) {
            return result;
        }
        CarMaintenance carMaintenance = modelMapperService.forRequest().map(createCarMaintenanceRequest,
                CarMaintenance.class);
        carMaintenanceDao.save(carMaintenance);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CARMAINTENANCEADD).getData());
    }


    @Override
    public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
        Result result = BusinessRules.run(existsByCarMaintenanceId(updateCarMaintenanceRequest.getCarMaintenanceId()),
                maintenanceDateAndReturnDateComparison(updateCarMaintenanceRequest.getCarMaintenanceId(), updateCarMaintenanceRequest.getReturnDate()));
        if (result != null) {
            return result;
        }
        updateCarMaintenanceRequest.setCarId(carMaintenanceDao.getById(updateCarMaintenanceRequest.getCarMaintenanceId()).getCar().getId());
        updateCarMaintenanceRequest.setMaintenanceDate(carMaintenanceDao.getById(updateCarMaintenanceRequest.getCarMaintenanceId()).getMaintenanceDate());
        updateCarMaintenanceRequest.setExpectedReturnDate(carMaintenanceDao.getById(updateCarMaintenanceRequest.getCarMaintenanceId()).getExpectedReturnDate());
        CarMaintenance carMaintenance = modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
        this.carMaintenanceDao.save(carMaintenance);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CARMAINTENANCEUPDATE).getData());
    }

    @Override
    public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
        Result result = BusinessRules.run(existsByCarMaintenanceId(deleteCarMaintenanceRequest.getCarMaintenanceId()));
        if (result != null) {
            return result;
        }
        CarMaintenance carMaintenance = modelMapperService.forRequest().map(deleteCarMaintenanceRequest, CarMaintenance.class);
        this.carMaintenanceDao.delete(carMaintenance);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CARMAINTENANCEDELETE).getData());
    }

    @Override
    public DataResult<List<CarMaintenanceDto>> getAll() {
        List<CarMaintenance> result = this.carMaintenanceDao.findAll();
        List<CarMaintenanceDto> response = result.stream()
                .map(carmaintanence -> modelMapperService.forDto().map(carmaintanence, CarMaintenanceDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<CarMaintenanceDto>>(response, this.languageWordService.getValueByKey(Messages.CARMAINTENANCELIST).getData());
    }

    @Override
    public Result checkIfCarReturnedFromMaintenance(int carId) {
        List<CarMaintenance> carMaintenances = this.carMaintenanceDao.findAllCarMaintenanceByCarId(carId);

        for (CarMaintenance carMaintenance : carMaintenances) {
            if (carMaintenance.getReturnDate() == null) {

                return new ErrorResult(this.languageWordService.getValueByKey(Messages.RENTALMAINTENANCEERROR).getData());
            }

        }

        return new SuccessResult();
    }

    private Result existsByCarMaintenanceId(int carMaintanenceId) {
        boolean result = this.carMaintenanceDao.existsById(carMaintanenceId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CARMAINTENANCENOTFOUND).getData());
        }
        return new SuccessResult();
    }

    private Result maintenanceDateAndReturnDateComparison(int carMaintenanceId, LocalDate returnDate) {
        Result result = BusinessRules.run(existsByCarMaintenanceId(carMaintenanceId));
        if (result != null) {
            return result;
        }
        LocalDate maintenanceDate = this.carMaintenanceDao.getById(carMaintenanceId).getMaintenanceDate();
        if (maintenanceDate.isAfter(returnDate)) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.RETURNDATENOTBEFOREMAINTENANCEDATE).getData());
        }
        return new SuccessResult();
    }

    private Result maintenanceDateAndExpectedReturnDateComparison(LocalDate maintenanceDate, LocalDate expectedReturnDate) {


        if (maintenanceDate.isAfter(expectedReturnDate)) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.EXPECTEDDATENOTBEFOREMAINTENANCEDATE).getData());
        }
        return new SuccessResult();
    }


}
