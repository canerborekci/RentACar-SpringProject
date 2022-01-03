package com.etiya.rentACarSpring.business.abstracts;

import com.etiya.rentACarSpring.business.dtos.KeySearchListDto;
import com.etiya.rentACarSpring.business.requests.key.CreateKeyRequest;
import com.etiya.rentACarSpring.business.requests.key.DeleteKeyRequest;
import com.etiya.rentACarSpring.business.requests.key.UpdateKeyRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.entities.Key;

import java.util.List;

public interface KeyService {
    Result add(CreateKeyRequest createKeyRequest);
    Result update(UpdateKeyRequest updateKeyRequest);
    Result delete(DeleteKeyRequest deleteKeyRequest);

    DataResult<Key> getByKey(String key);

    DataResult<List<KeySearchListDto>> getAll();
    Result checkIfMessageKeyNameNotExists(String messageKey);
}
