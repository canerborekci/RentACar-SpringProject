package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.RentalsAdditionalService;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.dtos.RentalAdditionalListDto;
import com.etiya.rentACarSpring.business.dtos.RentalsAdditionalSearchListDto;
import com.etiya.rentACarSpring.business.requests.Additional.CreateRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.Additional.DeleteRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.Additional.UpdateRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.rentalsAddiitional.CreateRentalsAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.rentalsAddiitional.DeleteRentalsAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.rentalsAddiitional.UpdateRentalsAdditionalRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.entities.RentalsAdditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/rentalsadditional")
public class RentalsAdditionalController {
    private RentalsAdditionalService rentalsAdditionalService;
    @Autowired
    public RentalsAdditionalController(RentalsAdditionalService rentalsAdditionalService) {
        this.rentalsAdditionalService = rentalsAdditionalService;
    }

    @GetMapping("all")
    public DataResult<List<RentalsAdditionalSearchListDto>> getAll(){

        return this.rentalsAdditionalService.getAll();
    }
    @PostMapping("add")
    public Result save(@RequestBody @Valid CreateRentalsAdditionalRequest createRentalsAdditionalRequest) {
        return this.rentalsAdditionalService.add(createRentalsAdditionalRequest);

    }
    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateRentalsAdditionalRequest updateRentalsAdditionalRequest) {
        return this.rentalsAdditionalService.update(updateRentalsAdditionalRequest);

    }
    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteRentalsAdditionalRequest deleteRentalsAdditionalRequest) {
        return this.rentalsAdditionalService.delete(deleteRentalsAdditionalRequest);

    }




}
