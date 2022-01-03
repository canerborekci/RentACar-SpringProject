package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.BrandService;
import com.etiya.rentACarSpring.business.dtos.BrandSearchListDto;
import com.etiya.rentACarSpring.business.requests.Brand.CreateBrandRequest;
import com.etiya.rentACarSpring.business.requests.Brand.DeleteBrandRequest;
import com.etiya.rentACarSpring.business.requests.Brand.UpdateBrandRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.BrandDao;
import com.etiya.rentACarSpring.entities.Brand;

@Service
public class BrandManager implements BrandService {
    private BrandDao brandDao;
    private ModelMapperService modelMapperService;
    private LanguageWordService languageWordService;

    @Autowired
    public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService, LanguageWordService languageWordService) {
        this.brandDao = brandDao;
        this.modelMapperService = modelMapperService;
        this.languageWordService = languageWordService;
    }

    @Override
    public DataResult<List<BrandSearchListDto>> getAll() {
        List<Brand> result = this.brandDao.findAll();
        List<BrandSearchListDto> response = result.stream().map(brand -> modelMapperService.forDto()
                .map(brand, BrandSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<BrandSearchListDto>>(response, this.languageWordService.getValueByKey(Messages.BRANDLIST).getData());
    }

    @Override
    public Result save(CreateBrandRequest createBrandRequest) {
        Result result = BusinessRules.run(existsByBrandName(createBrandRequest.getBrandName()));

        if (result != null) {
            return result;
        }
        Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        this.brandDao.save(brand);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.BRANDADD).getData());

    }

    @Override
    public Result update(UpdateBrandRequest updateBrandRequest) {
        Result result = BusinessRules.run(existsByBrandId(updateBrandRequest.getBrandId()));
        if (result != null) {
            return result;
        }
        Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        this.brandDao.save(brand);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.BRANDUPDATE).getData());
    }

    @Override
    public Result delete(DeleteBrandRequest deleteBrandRequest) {
        Result result = BusinessRules.run(existsByBrandId(deleteBrandRequest.getId()));
        if (result != null) {
            return result;
        }
        Brand brand = modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
        this.brandDao.delete(brand);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.BRANDDELETE).getData());
    }

    @Override
    public Result existsCarByBrandId(int brandId) {
        boolean result = this.brandDao.existsById(brandId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.BRANDNOTFOUND).getData());
        }
        return new SuccessResult();
    }


    private Result existsByBrandName(String brandName) {
        Brand brand = this.brandDao.getByBrandName(brandName);
        if (brand != null) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.BRANDCANTREPEAT).getData());
        }
        return new SuccessResult();
    }

    private Result existsByBrandId(int brandId) {
        boolean result = this.brandDao.existsById(brandId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.BRANDNOTFOUND).getData());
        }
        return new SuccessResult();
    }


}
