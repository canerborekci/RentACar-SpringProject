package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.requests.languageword.CreateLanguageWordRequest;
import com.etiya.rentACarSpring.business.requests.languageword.DeleteLanguageWordRequest;
import com.etiya.rentACarSpring.business.requests.languageword.UpdateLanguageWordRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/languageWords")
public class LanguageWordsController {
    private LanguageWordService languageWordService;
    @Autowired
    public LanguageWordsController(LanguageWordService languageWordService) {
        this.languageWordService = languageWordService;
    }
    @PostMapping("add")
    public Result add(@RequestBody CreateLanguageWordRequest createLanguageWordRequest) {
        return this.languageWordService.add(createLanguageWordRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody UpdateLanguageWordRequest updateLanguageWordRequest) {
        return this.languageWordService.update(updateLanguageWordRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody DeleteLanguageWordRequest deleteLanguageWordRequest) {
        return this.languageWordService.delete(deleteLanguageWordRequest);
    }
}
