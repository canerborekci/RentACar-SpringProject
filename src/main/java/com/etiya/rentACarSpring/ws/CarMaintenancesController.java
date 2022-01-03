package com.etiya.rentACarSpring.ws;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.CarMaintenanceService;
import com.etiya.rentACarSpring.business.dtos.CarMaintenanceDto;
import com.etiya.rentACarSpring.business.requests.maintenance.CreateCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.maintenance.DeleteCarMaintenanceRequest;
import com.etiya.rentACarSpring.business.requests.maintenance.UpdateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

@RestController
@RequestMapping("api/carMaintenance")
public class CarMaintenancesController {
	private CarMaintenanceService carMaintenanceService;
    
	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		Result result= this.carMaintenanceService.add(createCarMaintenanceRequest);
		return result;
	}
	@GetMapping("all")
	public DataResult<List<CarMaintenanceDto>> getAll(){
		return this.carMaintenanceService.getAll();
		
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
	}

}
