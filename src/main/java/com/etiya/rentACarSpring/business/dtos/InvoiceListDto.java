package com.etiya.rentACarSpring.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceListDto {


//    private int invoiceId;

    private String invoiceNo;

    private LocalDate creationDate;

    private double totalPrice;

    private int rentalId;

}
