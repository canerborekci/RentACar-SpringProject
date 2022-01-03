package com.etiya.rentACarSpring.business.requests.invoice;

import com.etiya.rentACarSpring.entities.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
    @JsonIgnore
    @NotNull
    private int invoiceId;
    @JsonIgnore
    private String invoiceNo;
    @NotNull
    @ApiModelProperty(example = "2022-01-01")
    private LocalDate creationDate;
    @JsonIgnore
    private double totalPrice;
    @NotNull
    private int rentalId;
}
