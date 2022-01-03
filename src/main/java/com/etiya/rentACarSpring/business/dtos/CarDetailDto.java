package com.etiya.rentACarSpring.business.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailDto {
    private int carId;
    private String colorName;
    private String brandName;
    private int dailyPrice;
    private int modelYear;
    private String description;
    private List<String> carImagePaths;
}
