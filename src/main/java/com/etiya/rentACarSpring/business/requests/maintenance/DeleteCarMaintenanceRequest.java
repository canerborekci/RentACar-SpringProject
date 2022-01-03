package com.etiya.rentACarSpring.business.requests.maintenance;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarMaintenanceRequest {
	@NotNull
    private int carMaintenanceId;

}
