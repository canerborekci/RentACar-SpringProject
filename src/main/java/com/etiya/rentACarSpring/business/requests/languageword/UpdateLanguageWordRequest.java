package com.etiya.rentACarSpring.business.requests.languageword;

import com.etiya.rentACarSpring.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLanguageWordRequest {
    private int id;

    private int keyId;

    private Language language;

    private String translation;
}
