package com.etiya.rentACarSpring.business.requests.languageword;

import com.etiya.rentACarSpring.entities.Key;
import com.etiya.rentACarSpring.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLanguageWordRequest {

    private int id;

    private int keyId;

    private int languageId;

    private String translation;
}
