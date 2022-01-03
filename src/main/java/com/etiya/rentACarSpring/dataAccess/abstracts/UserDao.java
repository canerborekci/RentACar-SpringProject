package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.business.dtos.UserSearchListDto;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.entities.User;

public interface UserDao extends JpaRepository<User, Integer>{
	List<User> getByEmail(String email);//user
	boolean existsByEmail(String email);
	
}
