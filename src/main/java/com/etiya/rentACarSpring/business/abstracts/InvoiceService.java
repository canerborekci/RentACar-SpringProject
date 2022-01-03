package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.InvoiceListDto;
import com.etiya.rentACarSpring.business.dtos.UsersInvoicesDto;
import com.etiya.rentACarSpring.business.requests.invoice.CreateInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.invoice.DeleteInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.invoice.UpdateInvoiceRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {
    Result save(CreateInvoiceRequest createInvoiceRequest);
    Result update(UpdateInvoiceRequest updateInvoiceRequest);
    Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
    Result existsByRentalId(int rentalId);
    DataResult<List<InvoiceListDto>> getAll();
    DataResult<List<UsersInvoicesDto>> getByUserInvoices(int userId);
    DataResult<List<InvoiceListDto>> getByCreateDateBetweenBeginDateAndEndDate(LocalDate beginDate, LocalDate endDate);


}
