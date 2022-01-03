package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.CarDamageService;
import com.etiya.rentACarSpring.business.dtos.CarDamageListDto;
import com.etiya.rentACarSpring.business.requests.carDamage.CreateCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.carDamage.DeleteCarDamageRequest;
import com.etiya.rentACarSpring.business.requests.carDamage.UpdateCarDamageRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/cardamages")
public class CarDamagesController {
    private CarDamageService carDamageService;
    @Autowired
    public CarDamagesController(CarDamageService carDamageService) {
        this.carDamageService = carDamageService;
    }
    @GetMapping("all")
    public DataResult<List<CarDamageListDto>> getAll(){

        return this.carDamageService.getAll();
    }
    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCarDamageRequest createCarDamageRequest) {
        return this.carDamageService.save(createCarDamageRequest);

    }
    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateCarDamageRequest updateCarDamageRequest) {
        return this.carDamageService.update(updateCarDamageRequest);

    }
    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteCarDamageRequest deleteCarDamageRequest) {
        return this.carDamageService.delete(deleteCarDamageRequest);

    }
}
