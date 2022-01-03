package com.etiya.rentACarSpring.business.requests.Additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalAdditionalRequest {
    @NotNull
    private int additionalId;
    @NotNull
    private String additionalName;
    @NotNull
    private int dailyPrice;

}
