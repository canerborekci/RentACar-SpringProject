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

import com.etiya.rentACarSpring.business.abstracts.ColorService;
import com.etiya.rentACarSpring.business.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.business.requests.color.CreateColorRequest;
import com.etiya.rentACarSpring.business.requests.color.DeleteColorRequest;
import com.etiya.rentACarSpring.business.requests.color.UpdateColorRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

@RestController
@RequestMapping("api/colors")
public class ColorsController {
	private ColorService colorService;
    
	@Autowired
	public ColorsController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}

	
	@GetMapping("all")
	public DataResult<List<ColorSearchListDto>> getAll(){
		return this.colorService.getAll();	
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) {
		return this.colorService.save(createColorRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) {	
		return this.colorService.update(updateColorRequest);
	}
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest) {
		return this.colorService.delete(deleteColorRequest);
	}
	
	
	

}
