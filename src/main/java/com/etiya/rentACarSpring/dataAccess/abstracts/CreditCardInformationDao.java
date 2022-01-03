package com.etiya.rentACarSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.CreditCardInformation;

public interface CreditCardInformationDao extends JpaRepository<CreditCardInformation, Integer>{
    int countCreditCardInformationByUser_Id(int user_id);
    boolean existsById(int cardInformationId);

}
