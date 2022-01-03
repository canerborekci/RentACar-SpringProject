package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.InvoiceService;
import com.etiya.rentACarSpring.business.dtos.InvoiceListDto;
import com.etiya.rentACarSpring.business.dtos.UsersInvoicesDto;
import com.etiya.rentACarSpring.business.requests.invoice.CreateInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.invoice.DeleteInvoiceRequest;
import com.etiya.rentACarSpring.business.requests.invoice.UpdateInvoiceRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/invoices")
public class InvoicesController {
    private InvoiceService invoiceService;
    @Autowired
    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @GetMapping("all")
    public DataResult<List<InvoiceListDto>> getAll(){
        return this.invoiceService.getAll();
    }
    @GetMapping("getByUserInvoices")
    public DataResult<List<UsersInvoicesDto>> getByUserInvoices(int userId){
        return this.invoiceService.getByUserInvoices(userId);
    }
    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateInvoiceRequest createInvoiceRequest) {
        return this.invoiceService.save(createInvoiceRequest);

    }

    @GetMapping("getInvoiceByDate")
    public DataResult<List<InvoiceListDto>> getInvoiceDateBetween(
            @RequestParam String  beginDate, @RequestParam String endDate){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate bDate = LocalDate.parse(beginDate,dateTimeFormatter);
        LocalDate eDate = LocalDate.parse(endDate,dateTimeFormatter);
        return this.invoiceService.getByCreateDateBetweenBeginDateAndEndDate(bDate, eDate);
    }
    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest) {
        return this.invoiceService.update(updateInvoiceRequest);

    }
    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteInvoiceRequest deleteInvoiceRequest) {
        return this.invoiceService.delete(deleteInvoiceRequest);

    }
}
