package com.etiya.rentACarSpring.business.requests.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteInvoiceRequest {
    @NotNull
    private int invoiceId;
}
