package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.LanguageSearchListDto;
import com.etiya.rentACarSpring.business.requests.language.CreateLanguageRequest;
import com.etiya.rentACarSpring.business.requests.language.DeleteLanguageRequest;
import com.etiya.rentACarSpring.business.requests.language.UpdateLanguageRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

import java.util.List;

public interface LanguageService {
    Result add(CreateLanguageRequest createLanguageRequest);
    Result update(UpdateLanguageRequest updateLanguageRequest);
    Result delete(DeleteLanguageRequest deleteLanguageRequest);
    DataResult<LanguageSearchListDto> getById(int id);
    DataResult<List<LanguageSearchListDto>> getAll();
    Result existsByLanguageId(int languageId);
}
