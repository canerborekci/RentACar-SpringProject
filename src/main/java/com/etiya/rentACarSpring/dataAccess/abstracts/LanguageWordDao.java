package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.LanguageWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageWordDao extends JpaRepository<LanguageWord, Integer> {
    LanguageWord getByLanguageIdAndKeyId(int languageId,int keyId);
    boolean existsByTranslation(String languageWord);
    boolean existsByLanguageIdAndKeyId(int languageId, int keyId);

}
