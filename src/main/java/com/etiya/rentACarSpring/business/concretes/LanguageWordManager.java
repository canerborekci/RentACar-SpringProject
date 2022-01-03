package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.KeyService;
import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.LanguageWordSearchListDto;
import com.etiya.rentACarSpring.business.requests.languageword.CreateLanguageWordRequest;
import com.etiya.rentACarSpring.business.requests.languageword.DeleteLanguageWordRequest;
import com.etiya.rentACarSpring.business.requests.languageword.UpdateLanguageWordRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.LanguageWordDao;
import com.etiya.rentACarSpring.entities.LanguageWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageWordManager implements LanguageWordService {
    private LanguageWordDao languageWordDao;
    private ModelMapperService modelMapperService;
    private KeyService keyService;
    @Value("${message.languageId}")
    private int languageId;


    @Autowired
    public LanguageWordManager(LanguageWordDao languageWordDao, ModelMapperService modelMapperService, @Lazy KeyService keyService) {
        this.languageWordDao = languageWordDao;
        this.modelMapperService = modelMapperService;
        this.keyService = keyService;
    }


    @Override
    public Result add(CreateLanguageWordRequest createLanguageWordRequest) {
        Result result = BusinessRules.run(checkIfLanguageWordNameExists(createLanguageWordRequest.getTranslation()));
        if (result != null) {
            return result;
        }
        LanguageWord languageWord = this.modelMapperService.forRequest().map(createLanguageWordRequest, LanguageWord.class);
        this.languageWordDao.save(languageWord);
        return new SuccessResult(this.getValueByKey(Messages.LANGUAGEWORDADD).getData());


    }

    @Override
    public Result update(UpdateLanguageWordRequest updateLanguageWordRequest) {
        Result result = BusinessRules.run(checkIfLanguageWordNameExists(updateLanguageWordRequest.getTranslation()), existsByLanguageWordId(updateLanguageWordRequest.getId()));
        if (result != null) {
            return result;
        }
        LanguageWord languageWord = this.modelMapperService.forRequest().map(updateLanguageWordRequest, LanguageWord.class);
        this.languageWordDao.save(languageWord);
        return new SuccessResult(this.getValueByKey(Messages.LANGUAGEWORDUPDATE).getData());
    }

    @Override
    public Result delete(DeleteLanguageWordRequest deleteLanguageWordRequest) {
        Result result = BusinessRules.run(existsByLanguageWordId(deleteLanguageWordRequest.getId()));
        if (result != null) {
            return result;
        }

        LanguageWord languageWord = this.modelMapperService.forRequest().map(deleteLanguageWordRequest, LanguageWord.class);
        this.languageWordDao.delete(languageWord);
        return new SuccessResult(this.getValueByKey(Messages.LANGUAGEWORDDELETE).getData());
    }

    @Override
    public DataResult<String> getValueByKey(String key) {

        String message = findByLanguageIdAndKeyId(this.keyService.getByKey(key).getData().getId()).getData();
        return new SuccessDataResult<String>(message);

    }

    @Override
    public DataResult<List<LanguageWordSearchListDto>> getAll() {

        List<LanguageWord> result = this.languageWordDao.findAll();
        List<LanguageWordSearchListDto> response = result.stream().map(languageWord -> modelMapperService.forDto().map(languageWord, LanguageWordSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<LanguageWordSearchListDto>>(response, getValueByKey(Messages.LANGUAGEWORDLIST).getData());
    }

    private DataResult<String> findByLanguageIdAndKeyId(int keyId) {
        String words = this.languageWordDao.getByLanguageIdAndKeyId(this.languageId, keyId).getTranslation();
        return new SuccessDataResult<String>(words);
    }

    private Result checkIfLanguageWordNameExists(String languageWord) {
        if (this.languageWordDao.existsByTranslation(languageWord)) {
            return new ErrorResult(getValueByKey(Messages.LANGUAGEWORDNAMEERROR).getData());
        }
        return new SuccessResult();
    }

    private Result existsByLanguageWordId(int messageKeyId) {
        if (!this.languageWordDao.existsById(messageKeyId)) {
            return new ErrorResult(getValueByKey(Messages.LANGUAGEWORDNOTFOUND).getData());
        }
        return new SuccessResult();

    }
}
