package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.LanguageService;
import com.etiya.rentACarSpring.business.requests.language.CreateLanguageRequest;
import com.etiya.rentACarSpring.business.requests.language.DeleteLanguageRequest;
import com.etiya.rentACarSpring.business.requests.language.UpdateLanguageRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/languages")
public class LanguagesController {
    private LanguageService languageService;
    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateLanguageRequest createLanguageRequest) {
        return this.languageService.add(createLanguageRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateLanguageRequest updateLanguageRequest) {
        return this.languageService.update(updateLanguageRequest);
    }
    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteLanguageRequest deleteLanguageRequest) {
        return this.languageService.delete(deleteLanguageRequest);
    }


}
