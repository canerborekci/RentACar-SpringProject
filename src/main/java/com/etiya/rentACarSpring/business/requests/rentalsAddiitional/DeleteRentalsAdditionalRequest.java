package com.etiya.rentACarSpring.business.requests.rentalsAddiitional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRentalsAdditionalRequest {
    @NotNull
    private int id;
}
