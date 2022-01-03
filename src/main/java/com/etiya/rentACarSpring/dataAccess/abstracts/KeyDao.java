package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyDao extends JpaRepository<Key,Integer> {
    Key findByKey(String key);
    boolean existsByKey(String key);
}
