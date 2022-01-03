package com.etiya.rentACarSpring.business.requests.maintenance;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIfCarRetunedFromMaintanenceRequest {
	@NotNull
    private int carMaintenanceId;
	@NotNull
	@ApiModelProperty(example = "2022-01-01")
	private LocalDate returnDate;
	@NotNull
	private String description;
}
