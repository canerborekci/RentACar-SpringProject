package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.ColorService;
import com.etiya.rentACarSpring.business.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.business.requests.color.CreateColorRequest;
import com.etiya.rentACarSpring.business.requests.color.DeleteColorRequest;
import com.etiya.rentACarSpring.business.requests.color.UpdateColorRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.ColorDao;
import com.etiya.rentACarSpring.entities.Color;

@Service
public class ColorManager implements ColorService {
    private ColorDao colorDao;
    private ModelMapperService modelMapperService;
    private LanguageWordService languageWordService;

    @Autowired
    public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService, LanguageWordService languageWordService) {
        this.colorDao = colorDao;
        this.modelMapperService = modelMapperService;
        this.languageWordService = languageWordService;
    }

    @Override
    public DataResult<List<ColorSearchListDto>> getAll() {
        List<Color> result = this.colorDao.findAll();

        List<ColorSearchListDto> response = result.stream()
                .map(color -> modelMapperService.forDto().map(color, ColorSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ColorSearchListDto>>(response, this.languageWordService.getValueByKey(Messages.COLORLIST).getData());
    }

    @Override
    public Result save(CreateColorRequest createColorRequest) {
        Result result = BusinessRules.run(existsByColorName(createColorRequest.getColorName()));

        if (result != null) {
            return result;
        }
        Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
        this.colorDao.save(color);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.COLORADD).getData());

    }

    @Override
    public Result update(UpdateColorRequest updateColorRequest) {
        Result result = BusinessRules.run(existsByColorId(updateColorRequest.getColorId()));
        if (result != null) {
            return result;
        }
        Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
        this.colorDao.save(color);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.COLORUPDATE).getData());
    }

    @Override
    public Result delete(DeleteColorRequest deleteColorRequest) {
        Result result = BusinessRules.run(existsByColorId(deleteColorRequest.getId()));
        if (result != null) {
            return result;
        }
        Color color = modelMapperService.forRequest().map(deleteColorRequest, Color.class);
        this.colorDao.delete(color);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.COLORDELETE).getData());
    }

    @Override
    public Result existCarByColorId(int colorId) {
        boolean result = this.colorDao.existsById(colorId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.COLORNOTFOUND).getData());
        }
        return new SuccessResult();
    }

    private Result existsByColorId(int colorId) {
        boolean result = this.colorDao.existsById(colorId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.COLORNOTFOUND).getData());
        }
        return new SuccessResult();
    }

    private Result existsByColorName(String colorName) {
        Color color = this.colorDao.getByColorName(colorName);
        if (color != null) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.COLORERROR).getData());
        }
        return new SuccessResult();
    }


}
