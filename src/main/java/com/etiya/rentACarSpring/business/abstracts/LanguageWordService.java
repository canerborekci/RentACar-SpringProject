package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.LanguageWordSearchListDto;
import com.etiya.rentACarSpring.business.requests.languageword.CreateLanguageWordRequest;
import com.etiya.rentACarSpring.business.requests.languageword.DeleteLanguageWordRequest;
import com.etiya.rentACarSpring.business.requests.languageword.UpdateLanguageWordRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

import java.util.List;

public interface LanguageWordService {
    Result add(CreateLanguageWordRequest createLanguageWordRequest);
    Result update(UpdateLanguageWordRequest updateLanguageWordRequest);
    Result delete(DeleteLanguageWordRequest deleteLanguageWordRequest);
    DataResult<String> getValueByKey(String key);
    DataResult<List<LanguageWordSearchListDto>> getAll();
}
