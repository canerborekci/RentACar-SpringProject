package com.etiya.rentACarSpring.business.requests.Additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRentalAdditionalRequest {
    @NotNull
    private int additionalId;
}
