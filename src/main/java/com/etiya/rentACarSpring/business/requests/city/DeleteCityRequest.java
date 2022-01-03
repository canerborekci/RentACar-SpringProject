package com.etiya.rentACarSpring.business.requests.city;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCityRequest {
    @NotNull
    private int cityId;

}
