package com.etiya.rentACarSpring.business.requests.city;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {
    @NotNull
    private int cityId;

    @NotNull
    @NotBlank
    private String cityName;
}
