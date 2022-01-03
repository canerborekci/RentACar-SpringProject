package com.etiya.rentACarSpring.business.concretes;

import com.etiya.rentACarSpring.business.abstracts.KeyService;
import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.business.dtos.KeySearchListDto;
import com.etiya.rentACarSpring.business.requests.key.CreateKeyRequest;
import com.etiya.rentACarSpring.business.requests.key.DeleteKeyRequest;
import com.etiya.rentACarSpring.business.requests.key.UpdateKeyRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.KeyDao;
import com.etiya.rentACarSpring.entities.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeyManager implements KeyService {
    private KeyDao keyDao;
    private ModelMapperService modelMapperService;
    private LanguageWordService languageWordService;

    @Autowired
    public KeyManager(KeyDao keyDao, ModelMapperService modelMapperService, LanguageWordService languageWordService) {
        this.keyDao = keyDao;
        this.modelMapperService = modelMapperService;
        this.languageWordService = languageWordService;
    }

    @Override
    public DataResult<List<KeySearchListDto>> getAll() {
        List<Key> result = this.keyDao.findAll();
        List<KeySearchListDto> response = result.stream().map(key -> modelMapperService.forDto().map(key, KeySearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<KeySearchListDto>>(response, this.languageWordService.getValueByKey(Messages.MESSAGEKEYLIST).getData());
    }

    @Override
    public Result add(CreateKeyRequest createKeyRequest) {
        Result result = BusinessRules.run(checkIfMessageKeyNameExists(createKeyRequest.getKey()));
        if (result != null) {
            return result;
        }
        Key key = modelMapperService.forRequest().map(createKeyRequest, Key.class);
        this.keyDao.save(key);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.MESSAGEKEYADD).getData());
    }

    @Override
    public Result update(UpdateKeyRequest updateKeyRequest) {
        Result result = BusinessRules.run(existsKeyId(updateKeyRequest.getId()), checkIfMessageKeyNameExists(updateKeyRequest.getKey()));
        if (result != null) {
            return result;
        }

        Key key = modelMapperService.forRequest().map(updateKeyRequest, Key.class);
        this.keyDao.save(key);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.MESSAGEKEYUPDATE).getData());
    }

    @Override
    public Result delete(DeleteKeyRequest deleteKeyRequest) {
        Result result = BusinessRules.run(existsKeyId(deleteKeyRequest.getId()));
        if (result != null) {
            return result;
        }
        Key key = modelMapperService.forRequest().map(deleteKeyRequest, Key.class);
        this.keyDao.delete(key);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.MESSAGEKEYDELETE).getData());
    }

    @Override
    public DataResult<Key> getByKey(String key) {
        return new SuccessDataResult<Key>(this.keyDao.findByKey(key));
    }

    private Result checkIfMessageKeyNameExists(String messageKey) {
        if (this.keyDao.existsByKey(messageKey)) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.MESSAGEKEYNAMEERROR).getData());
        }
        return new SuccessResult();
    }


    @Override
    public Result checkIfMessageKeyNameNotExists(String messageKey) {
        if (!this.keyDao.existsByKey(messageKey)) {
            return new ErrorResult();
        }
        return new SuccessResult();
    }

    private Result existsKeyId(int messageKeyId) {
        if (!this.keyDao.existsById(messageKeyId)) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.MESSAGEKEYNOTFOUND).getData());
        }
        return new SuccessResult();

    }

}
