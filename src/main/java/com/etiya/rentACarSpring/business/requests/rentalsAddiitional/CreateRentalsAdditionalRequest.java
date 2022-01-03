package com.etiya.rentACarSpring.business.requests.rentalsAddiitional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalsAdditionalRequest {
    @JsonIgnore
    private int id;
    @NotNull
    private int rentalId;
    @NotNull
    private int additionalId;
}
