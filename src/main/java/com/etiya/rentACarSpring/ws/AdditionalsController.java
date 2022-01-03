package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.AdditionalService;
import com.etiya.rentACarSpring.business.dtos.RentalAdditionalListDto;
import com.etiya.rentACarSpring.business.requests.Additional.CreateRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.Additional.DeleteRentalAdditionalRequest;
import com.etiya.rentACarSpring.business.requests.Additional.UpdateRentalAdditionalRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/rentaladditionals")
public class AdditionalsController {
    private AdditionalService rentalAdditionalService;
    @Autowired
    public AdditionalsController(AdditionalService rentalAdditionalService) {
        this.rentalAdditionalService = rentalAdditionalService;
    }

    @GetMapping("all")
    public DataResult<List<RentalAdditionalListDto>> getAll(){

        return this.rentalAdditionalService.getAll();
    }
    @PostMapping("add")
    public Result save(@RequestBody @Valid CreateRentalAdditionalRequest createRentalAdditionalRequest) {
        return this.rentalAdditionalService.save(createRentalAdditionalRequest);

    }
    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateRentalAdditionalRequest updateRentalAdditionalRequest) {
        return this.rentalAdditionalService.update(updateRentalAdditionalRequest);

    }
    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteRentalAdditionalRequest deleteRentalAdditionalRequest) {
        return this.rentalAdditionalService.delete(deleteRentalAdditionalRequest);

    }
}
