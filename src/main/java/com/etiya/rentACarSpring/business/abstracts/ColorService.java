package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.business.requests.color.CreateColorRequest;
import com.etiya.rentACarSpring.business.requests.color.DeleteColorRequest;
import com.etiya.rentACarSpring.business.requests.color.UpdateColorRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

public interface ColorService {
	DataResult<List<ColorSearchListDto>> getAll();
	Result save(CreateColorRequest createColorRequest);
	Result update(UpdateColorRequest updateColorRequest);
	Result delete(DeleteColorRequest deleteColorRequest);
	Result existCarByColorId(int colorId);

}
