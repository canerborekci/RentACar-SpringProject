package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.KeyService;
import com.etiya.rentACarSpring.business.requests.key.CreateKeyRequest;
import com.etiya.rentACarSpring.business.requests.key.DeleteKeyRequest;
import com.etiya.rentACarSpring.business.requests.key.UpdateKeyRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/keys")
public class KeysController {
    private KeyService keyService;

    @Autowired
    public KeysController(KeyService keyService) {
        this.keyService = keyService;
    }

    @PostMapping("add")
    public Result add(@RequestBody CreateKeyRequest createKeyRequest) {
        return this.keyService.add(createKeyRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody UpdateKeyRequest updateKeyRequest) {
        return this.keyService.update(updateKeyRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody DeleteKeyRequest deleteKeyRequest) {
        return this.keyService.delete(deleteKeyRequest);
    }
}
