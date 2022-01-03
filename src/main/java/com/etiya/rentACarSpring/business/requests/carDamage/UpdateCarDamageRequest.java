package com.etiya.rentACarSpring.business.requests.carDamage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {
    @NotNull
    private int carDamageId;
    @NotNull
    private String damageInformation;
    @NotNull
    private int carId;
}
