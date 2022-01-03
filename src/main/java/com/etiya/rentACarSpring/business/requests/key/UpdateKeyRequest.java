package com.etiya.rentACarSpring.business.requests.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateKeyRequest {
    private int id;
    private String key;
}
