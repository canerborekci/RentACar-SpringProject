package com.etiya.rentACarSpring.ws;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.rentACarSpring.business.abstracts.CarImageService;
import com.etiya.rentACarSpring.business.dtos.CarImageDto;
import com.etiya.rentACarSpring.business.dtos.CarImageListDto;
import com.etiya.rentACarSpring.business.requests.carImages.CreateCarImagesRequest;
import com.etiya.rentACarSpring.business.requests.carImages.DeleteCarImageRequest;
import com.etiya.rentACarSpring.business.requests.carImages.UpdateCarImageRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
@RestController
@RequestMapping("api/carImages")
public class CarImagesController {
	private CarImageService carImageService;

	public CarImagesController(CarImageService carImageService) {
		super();
		this.carImageService = carImageService;
	}
	
	@PostMapping("/add")
	public Result add(int carId, MultipartFile file) throws IOException {

		CreateCarImagesRequest createCarImageRequest = new CreateCarImagesRequest();
		createCarImageRequest.setCarId(carId);
		createCarImageRequest.setFile(file);

		return this.carImageService.add(createCarImageRequest);
	}
	
	@PutMapping("/update")
    public Result update(@RequestParam("id") int id,@RequestParam("carId") int carId, MultipartFile file) throws IOException {
        UpdateCarImageRequest updateCarImageRequest=new UpdateCarImageRequest();
        updateCarImageRequest.setId(id);
        updateCarImageRequest.setCarId(carId);
        updateCarImageRequest.setFile(file);
        return this.carImageService.update(updateCarImageRequest);
    }
	@DeleteMapping
    public Result delete(DeleteCarImageRequest deleteCarImagesRequest) throws IOException {
        return this.carImageService.delete(deleteCarImagesRequest);
    }
	@GetMapping("getAll")
    public DataResult<List<CarImageListDto>> getAll() {
        return this.carImageService.getAll();
    }
	@GetMapping("getImageByCarId")
    public DataResult<List<CarImageDto>> getImageByCarId(int carId) {
        return this.carImageService.getImageByCarId(carId);
    }
	
}
