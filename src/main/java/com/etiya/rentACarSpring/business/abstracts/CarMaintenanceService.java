package com.etiya.rentACarSpring.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.etiya.rentACarSpring.business.dtos.CarMaintenanceDto;
import com.etiya.rentACarSpring.business.requests.maintenance.CreateCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.maintenance.DeleteCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.maintenance.UpdateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

public interface CarMaintenanceService {
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);
	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);
	Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest);
	DataResult<List<CarMaintenanceDto>> getAll();
	Result checkIfCarReturnedFromMaintenance(int carId);
}
