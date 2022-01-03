package com.etiya.rentACarSpring.business.requests.posService;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosServiceRequest {
    @NotNull
    @NotBlank
    @Size(min = 16, max = 16)
    private String cardNumber;
    @NotNull
    @Size(max = 25)
    private String cardHolderName;
    @NotNull
    @NotBlank
    @Size(min = 5, max = 5)
    private String expirationDate;
    @NotNull
    @Size(min = 3, max = 3)
    private String cvv;
    @NotNull
    @NotBlank
    private double totalPrice;
}
