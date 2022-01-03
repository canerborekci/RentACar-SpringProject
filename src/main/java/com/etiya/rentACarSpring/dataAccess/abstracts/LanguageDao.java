package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.business.dtos.LanguageSearchListDto;
import com.etiya.rentACarSpring.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageDao extends JpaRepository<Language,Integer> {
    boolean existsByLanguageName(String languageName);
    LanguageSearchListDto getById(int id);
    boolean existsById(int id);
}
