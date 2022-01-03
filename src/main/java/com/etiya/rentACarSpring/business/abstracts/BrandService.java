package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.business.dtos.BrandSearchListDto;
import com.etiya.rentACarSpring.business.requests.Brand.CreateBrandRequest;
import com.etiya.rentACarSpring.business.requests.Brand.DeleteBrandRequest;
import com.etiya.rentACarSpring.business.requests.Brand.UpdateBrandRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

public interface BrandService {
	DataResult<List<BrandSearchListDto>> getAll();
	Result save(CreateBrandRequest createBrandRequest);
	Result update(UpdateBrandRequest updateBrandRequest);
	Result delete(DeleteBrandRequest deleteBrandRequest);
	Result existsCarByBrandId(int brandId);
    
}
