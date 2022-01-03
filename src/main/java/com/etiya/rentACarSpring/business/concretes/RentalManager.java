package com.etiya.rentACarSpring.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.*;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.CardInformationDto;
import com.etiya.rentACarSpring.business.requests.posService.PosServiceRequest;
import com.etiya.rentACarSpring.core.utilities.adapters.PaymentService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.business.requests.rental.CreateRentalRequest;
import com.etiya.rentACarSpring.business.requests.rental.DeleteRentalRequest;
import com.etiya.rentACarSpring.business.requests.rental.UpdateIfCarReturned;
import com.etiya.rentACarSpring.business.requests.rental.UpdateRentalRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.RentalDao;
import com.etiya.rentACarSpring.entities.Rental;

@Service
public class RentalManager implements RentalService {
    private RentalDao rentalDao;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private UserService userService;
    private CarMaintenanceService carMaintenanceService;
    private PaymentService paymentService;
    private InvoiceService invoiceService;
    private LanguageWordService languageWordService;
    private CityService cityService;

    @Autowired
    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService, UserService userService, @Lazy CarMaintenanceService carMaintenanceService, PaymentService paymentService, @Lazy InvoiceService invoiceService, LanguageWordService languageWordService, CityService cityService) {
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
        this.userService = userService;
        this.carMaintenanceService = carMaintenanceService;
        this.paymentService = paymentService;
        this.invoiceService = invoiceService;
        this.languageWordService = languageWordService;
        this.cityService = cityService;
    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) {
        Result result = BusinessRules.run(checkIfCarReturned(createRentalRequest.getCarId()), checkCompareUserAndCarFindeksScore(createRentalRequest.getUserId(), createRentalRequest.getCarId()), carMaintenanceService.checkIfCarReturnedFromMaintenance(createRentalRequest.getCarId()), cityService.existsByCityId(createRentalRequest.getTakeCityId()));

        if (result != null) {
            return result;
        }

        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);

        rentalDao.save(rental);

        return new SuccessResult(this.languageWordService.getValueByKey(Messages.RENTALADD).getData());
    }

    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) {
        Result result = BusinessRules.run(existByRentalId(deleteRentalRequest.getRentalId()));

        if (result != null) {
            return result;
        }
        Rental rental = modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
        this.rentalDao.delete(rental);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.RENTALDELETE).getData());
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        Result result = BusinessRules.run(checkIfCarReturned(updateRentalRequest.getCarId()), checkCompareUserAndCarFindeksScore(updateRentalRequest.getUserId(), updateRentalRequest.getCarId()), carMaintenanceService.checkIfCarReturnedFromMaintenance(updateRentalRequest.getCarId()), cityService.existsByCityId(updateRentalRequest.getTakeCityId()), existByRentalId(updateRentalRequest.getId()), invoiceService.existsByRentalId(updateRentalRequest.getId()), rentDateAndReturnDateComparison(updateRentalRequest.getId(), updateRentalRequest.getReturnDate()), rentKmAndReturnKmComparison(updateRentalRequest.getId(), updateRentalRequest.getReturnKilometer()));

        if (result != null) {
            return result;
        }
        Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        this.rentalDao.save(rental);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.RENTALUPDATE).getData());
    }

    @Override
    public Result updateIfCarReturned(UpdateIfCarReturned updateIfCarReturned) {
        Result result = BusinessRules.run(cityService.existsByCityId(updateIfCarReturned.getReturnCityId()),
                rentDateAndReturnDateComparison(updateIfCarReturned.getId(), updateIfCarReturned.getReturnDate()),
                rentKmAndReturnKmComparison(updateIfCarReturned.getId(), updateIfCarReturned.getReturnKilometer()),
                checkPaymentResult(updateIfCarReturned.getId(), updateIfCarReturned.getReturnDate(), updateIfCarReturned.getCardInformationDto()),
                existByRentalId(updateIfCarReturned.getId()));

        if (result != null) {
            return result;
        }

        Rental request = this.rentalDao.getById(updateIfCarReturned.getId());

        UpdateRentalRequest updateRentalRequest = modelMapperService.forRequest().map(request, UpdateRentalRequest.class);
        updateRentalRequest.setReturnCityId(updateIfCarReturned.getReturnCityId());
        updateRentalRequest.setReturnKilometer(updateIfCarReturned.getReturnKilometer());
        updateRentalRequest.setReturnDate(updateIfCarReturned.getReturnDate());

        Rental rental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        updateCityNameIfReturnCityIsDifferent(rental);
        updateKilometerIfCarDelivered(rental);
        this.rentalDao.save(rental);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CARISDELIVERED).getData());
    }


    @Override
    public DataResult<List<RentalSearchListDto>> getAll() {

        List<Rental> result = this.rentalDao.findAll();
        List<RentalSearchListDto> response = result.stream().map(rental -> modelMapperService.forDto().map(rental, RentalSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<RentalSearchListDto>>(response, this.languageWordService.getValueByKey(Messages.RENTALLIST).getData());
    }


    @Override
    public Result checkIfCarReturned(int carId) {
        Result result = BusinessRules.run(carService.existsByCarId(carId));

        if (result != null) {
            return result;
        }
        List<Rental> rentals = this.rentalDao.findAllRentalByCarId(carId);

        for (Rental rental : rentals) {
            if (rental.getReturnDate() == null) {
                return new ErrorResult(this.languageWordService.getValueByKey(Messages.RENTALMAINTENANCEERROR).getData());
            }
        }
        return new SuccessResult();
    }

    @Override
    public Result checkRentalIsCompleted(int rentalId) {
        Result result = BusinessRules.run(existByRentalId(rentalId));

        if (result != null) {
            return result;
        }

        LocalDate returnDate = getById(rentalId).getData().getReturnDate();
        if (returnDate == null) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.RENTALMAINTENANCEERROR).getData());
        }
        return new SuccessResult();
    }


    @Override
    public DataResult<Rental> getById(int rentalId) {
        Rental rental = this.rentalDao.getById(rentalId);
        return new SuccessDataResult<Rental>(rental, this.languageWordService.getValueByKey(Messages.RENTALFOUND).getData());
    }

    @Override
    public DataResult<Integer> getCountOfRentalDays(int id) {
        return new SuccessDataResult<>(this.rentalDao.getCountofRentalDays(id));
    }

    @Override
    public Result checkUserHasNoRental(int userId) {
        int counter = this.rentalDao.countRentalByUser_Id(userId);
        if (counter == 0) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.RENTALNOTFOUND).getData());
        }
        return new SuccessResult();
    }

    @Override
    public Result existByRentalId(int id) {
        boolean result = this.rentalDao.existsById(id);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.RENTALNOTFOUND).getData());
        }
        return new SuccessResult();
    }

    private Result rentDateAndReturnDateComparison(int id, LocalDate returnDate) {
        Result result = BusinessRules.run(existByRentalId(id));

        if (result != null) {
            return result;
        }
        LocalDate rentDate = this.rentalDao.getById(id).getRentDate();
        if (rentDate.isAfter(returnDate)) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.RETURNDATECANNOTBEFORERENTDATE).getData());
        }
        return new SuccessResult();


    }

    private Result rentKmAndReturnKmComparison(int id, int returnKilometer) {
        Result result = BusinessRules.run(existByRentalId(id));

        if (result != null) {
            return result;
        }
        int rentKm = this.rentalDao.getById(id).getStartKilometer();

        if (rentKm > returnKilometer) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.RETURNKMCANNOTLESSSTARTKM).getData());
        }
        return new SuccessResult();


    }


    private Result checkCompareUserAndCarFindeksScore(int userId, int carId) {
        Result result = BusinessRules.run(carService.existsByCarId(carId), userService.existsByUserId(userId));

        if (result != null) {
            return result;
        }

        DataResult<CarSearchListDto> car = this.carService.getById(carId);

        int user = this.userService.getById(userId).getData().getFindeksScore();
        if (car.getData().getFindeksScore() >= user) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.RENTALFINDEXSCOREERROR).getData());
        }
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.RENTALFINDEXSCORE).getData());
    }

    private void updateCityNameIfReturnCityIsDifferent(Rental rental) {
        if ((rental.getTakeCity().getCityId()) != (rental.getReturnCity().getCityId())) {
            this.carService.updateCityAfterCarDelivered(rental.getCar().getId(), rental.getReturnCity().getCityId());
        }
    }

    private void updateKilometerIfCarDelivered(Rental rental) {
        this.carService.updateKilometerAfterCarDelivered(rental.getCar().getId(), rental.getReturnKilometer());
    }

    private Result checkPaymentResult(int rentalId, LocalDate returnDate, CardInformationDto cardInformationDto) {
        Result result = BusinessRules.run(existByRentalId(rentalId));

        if (result != null) {
            return result;
        }
        Rental rental = this.rentalDao.getById(rentalId);
        int carDailyPrice = this.carService.getById(rental.getCar().getId()).getData().getDailyPrice();

        double totalPrice = carDailyPrice * totalRentDays(rentalId, returnDate);

        PosServiceRequest fakePosServiceRequest = new PosServiceRequest();
        fakePosServiceRequest.setCardNumber(cardInformationDto.getCardName());
        fakePosServiceRequest.setCardHolderName(cardInformationDto.getCardHolderName());
        fakePosServiceRequest.setExpirationDate(cardInformationDto.getExpirationDate());
        fakePosServiceRequest.setCvv(cardInformationDto.getCvv());
        fakePosServiceRequest.setTotalPrice(totalPrice);

        if (!this.paymentService.withdraw(fakePosServiceRequest)) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.INSUFFICIENTBALANCE).getData());
        }

        return new SuccessResult(this.languageWordService.getValueByKey(Messages.SUFFICIENTBALANCE).getData());
    }

    private int totalRentDays(int rentalId, LocalDate returnDate) {
        Rental rental = this.rentalDao.getById(rentalId);
        LocalDate returnDateCount = returnDate;
        LocalDate rentDateCount = rental.getRentDate();
        Period period = Period.between(rentDateCount, returnDateCount);
        return period.getDays();
    }


}
