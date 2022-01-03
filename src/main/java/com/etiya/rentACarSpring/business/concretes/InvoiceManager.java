package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.InvoiceService;
import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.abstracts.RentalService;
import com.etiya.rentACarSpring.business.abstracts.RentalsAdditionalService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.*;
import com.etiya.rentACarSpring.business.requests.invoice.CreateInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.invoice.DeleteInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.invoice.UpdateInvoiceRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.InvoiceDao;
import com.etiya.rentACarSpring.entities.Invoice;
import com.etiya.rentACarSpring.entities.Rental;
import com.etiya.rentACarSpring.entities.complexTypes.UsersInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceManager implements InvoiceService {
    private InvoiceDao invoiceDao;
    private ModelMapperService modelMapperService;

    private RentalService rentalService;
    private RentalsAdditionalService rentalsAdditionalService;
    private LanguageWordService languageWordService;

    @Autowired
    public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService, RentalService rentalService, RentalsAdditionalService rentalsAdditionalService, LanguageWordService languageWordService) {
        this.invoiceDao = invoiceDao;
        this.modelMapperService = modelMapperService;
        this.rentalsAdditionalService = rentalsAdditionalService;
        this.rentalService = rentalService;
        this.languageWordService = languageWordService;
    }


    @Override
    public Result save(CreateInvoiceRequest createInvoiceRequest) {
        Result result = BusinessRules.run(rentalService.existByRentalId(createInvoiceRequest.getRentalId()),

                existsByRentalId(createInvoiceRequest.getRentalId()), CreationDateAndRentalDateComparison(createInvoiceRequest.getCreationDate(), createInvoiceRequest.getRentalId()));

        if (result != null) {
            return result;
        }

        Rental rental = this.rentalService.getById(createInvoiceRequest.getRentalId()).getData();
        int extraPrice = 0;
        if ((rental.getTakeCity().getCityId()) != (rental.getReturnCity().getCityId())) {
            extraPrice = 500;
        }

        Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
        invoice.setTotalPrice(this.rentalService.getById(createInvoiceRequest.getRentalId()).getData().getCar().getDailyPrice() * totalRentDays(createInvoiceRequest.getRentalId()) + extraRentalPriceCalculator(createInvoiceRequest) + (getAdditionalPriceByRental(createInvoiceRequest.getRentalId()).getData() * totalRentDays(createInvoiceRequest.getRentalId())) + extraPrice);


        invoice.setInvoiceNo(createInvoiceNumber(createInvoiceRequest.getRentalId(), createInvoiceRequest.getCreationDate()).getData());

        this.invoiceDao.save(invoice);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.INVOICEADD).getData());
    }


    @Override
    public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
        Result result = BusinessRules.run(existsByInvoiceId(updateInvoiceRequest.getInvoiceId()), rentalService.existByRentalId(updateInvoiceRequest.getRentalId()));

        if (result != null) {
            return result;
        }
        Invoice invoice = modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
        this.invoiceDao.save(invoice);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.INVOICEUPDATE).getData());
    }

    @Override
    public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
        Result result = BusinessRules.run(existsByInvoiceId(deleteInvoiceRequest.getInvoiceId()));

        if (result != null) {
            return result;
        }
        Invoice invoice = modelMapperService.forRequest().map(deleteInvoiceRequest, Invoice.class);
        this.invoiceDao.delete(invoice);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.INVOICEDELETE).getData());
    }

    @Override
    public DataResult<List<InvoiceListDto>> getAll() {
        List<Invoice> result = this.invoiceDao.findAll();
        List<InvoiceListDto> response = result.stream().map(invoice -> modelMapperService.forDto().map(invoice, InvoiceListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<InvoiceListDto>>(response, this.languageWordService.getValueByKey(Messages.INVOICELIST).getData());
    }

    @Override
    public DataResult<List<UsersInvoicesDto>> getByUserInvoices(int userId) {
        List<UsersInvoice> result = this.invoiceDao.getUsersInvoices(userId);
        List<UsersInvoicesDto> response = result.stream().map(usersInvoice -> modelMapperService.forDto().map(usersInvoice, UsersInvoicesDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<UsersInvoicesDto>>(response, this.languageWordService.getValueByKey(Messages.INVOICEBYCUSTOMERLIST).getData());
    }

    @Override
    public DataResult<List<InvoiceListDto>> getByCreateDateBetweenBeginDateAndEndDate(LocalDate beginDate, LocalDate endDate) {
        List<Invoice> invoices = this.invoiceDao.findByCreationDateBetween(beginDate, endDate);
        List<InvoiceListDto> invoiceListDtos = invoices.stream().map(invoice -> modelMapperService.forDto().map(invoice, InvoiceListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<InvoiceListDto>>(invoiceListDtos);
    }

    @Override
    public Result existsByRentalId(int rentalId) {
        Invoice result = this.invoiceDao.getByRental_Id(rentalId);
        if (result != null) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.RENTALINVOICEEXIST).getData());
        }
        return new SuccessResult();
    }

    private int totalRentDays(int rentalId) {
        Rental rental = this.rentalService.getById(rentalId).getData();
        LocalDate returnDateCount = rental.getReturnDate();
        LocalDate rentDateCount = rental.getRentDate();
        Period period = Period.between(rentDateCount, returnDateCount);
        return period.getDays();
    }

    private DataResult<String> createInvoiceNumber(int rentalId, LocalDate creationDate) {

        String year = String.valueOf(creationDate.getYear());
        String month = String.valueOf(creationDate.getMonthValue());
        String day = String.valueOf(creationDate.getDayOfMonth());
        String rental = String.valueOf(rentalId);
        String invoiceNumber = year + month + day + rental;

        return new SuccessDataResult<>(invoiceNumber);
    }

    private Result existsByInvoiceId(int invoiceId) {
        boolean result = this.invoiceDao.existsById(invoiceId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.INVOICENOTFOUND).getData());
        }
        return new SuccessResult();
    }


    private Result CreationDateAndRentalDateComparison(LocalDate creationDate, int rentalId) {
        Result result = BusinessRules.run(rentalService.checkRentalIsCompleted(rentalId));

        if (result != null) {
            return result;
        }


        LocalDate returnDate = rentalService.getById(rentalId).getData().getReturnDate();
        if (creationDate.isBefore(returnDate)) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.INVOICEDATECANNOTBEFORERENTDATE).getData());
        }
        return new SuccessResult();
    }

    private int extraRentalPriceCalculator(CreateInvoiceRequest createInvoiceRequest) {
        Rental rental = this.rentalService.getById(createInvoiceRequest.getRentalId()).getData();
        int extraPrice = 0;
        if ((rental.getTakeCity().getCityId()) != (rental.getReturnCity().getCityId())) {
            extraPrice = 500;
        }
        return extraPrice;
    }


    private DataResult<Integer> getAdditionalPriceByRental(int rentalId) {
        int additionalDailyTotalPrice = 0;

        List<RentalsAdditionalSearchListDto> totalAdditionalPriceByRental = this.rentalsAdditionalService.getByRentalId(rentalId);

        for (RentalsAdditionalSearchListDto rentalsAdditional : totalAdditionalPriceByRental) {

            additionalDailyTotalPrice += rentalsAdditional.getPrice();
        }
        return new SuccessDataResult<Integer>(additionalDailyTotalPrice);
    }


}
