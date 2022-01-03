package com.etiya.rentACarSpring.business.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageListDto {
    private int id;

    private int carDamageId;

    private String damageInformation;

    private int carId;
}
