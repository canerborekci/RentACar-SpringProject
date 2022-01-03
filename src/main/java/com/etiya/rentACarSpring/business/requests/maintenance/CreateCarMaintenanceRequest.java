package com.etiya.rentACarSpring.business.requests.maintenance;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {
	@JsonIgnore
	private int carMaintenanceId;

	@NotNull
	@ApiModelProperty(example = "2022-01-01")
	private LocalDate maintenanceDate;
	@NotNull
	@ApiModelProperty(example = "2022-01-01")
	private LocalDate expectedReturnDate;
	@JsonIgnore
	private LocalDate returnDate;

	@NotNull
	private String description;
	@NotNull
	private int carId;

}
