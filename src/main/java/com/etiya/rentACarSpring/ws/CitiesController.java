package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.CityService;
import com.etiya.rentACarSpring.business.dtos.CitySearchListDto;
import com.etiya.rentACarSpring.business.requests.city.CreateCityRequest;
import com.etiya.rentACarSpring.business.requests.city.DeleteCityRequest;
import com.etiya.rentACarSpring.business.requests.city.UpdateCityRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(("api/cities"))
public class CitiesController {
    private CityService cityService;
    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }
    @GetMapping("all")
    public DataResult<List<CitySearchListDto>> getAll(){

        return this.cityService.getAll();
    }
    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCityRequest createCityRequest) {
        return this.cityService.save(createCityRequest);

    }
    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateCityRequest updateCityRequest) {
        return this.cityService.update(updateCityRequest);

    }
    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteCityRequest deleteCityRequest) {
        return this.cityService.delete(deleteCityRequest);

    }

}
