package com.etiya.rentACarSpring.business.requests.invoice;

import com.etiya.rentACarSpring.entities.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
    @NotNull
    private int invoiceId;
    @NotNull
    private String invoiceNo;
    @NotNull
    @ApiModelProperty(example = "2022-01-01")
    private LocalDate creationDate;
    @NotNull
    private double totalPrice;
    @NotNull
    private int rentalId;
}
