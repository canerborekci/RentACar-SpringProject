package com.etiya.rentACarSpring.business.abstracts;

import java.io.IOException;
import java.util.List;

import com.etiya.rentACarSpring.business.dtos.CarImageDto;
import com.etiya.rentACarSpring.business.dtos.CarImageListDto;
import com.etiya.rentACarSpring.business.requests.carImages.CreateCarImagesRequest;
import com.etiya.rentACarSpring.business.requests.carImages.DeleteCarImageRequest;
import com.etiya.rentACarSpring.business.requests.carImages.UpdateCarImageRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

public interface CarImageService {
	DataResult<List<CarImageListDto>> getAll();
	DataResult<List<CarImageDto>> getImageByCarId(int carId);
	Result add(CreateCarImagesRequest createCarImageRequest) throws IOException;
	Result update(UpdateCarImageRequest updateCarImageRequest) throws IOException;
	Result delete(DeleteCarImageRequest deleteCarImageRequest);

	
}
