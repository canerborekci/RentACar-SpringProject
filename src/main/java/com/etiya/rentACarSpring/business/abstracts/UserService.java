package com.etiya.rentACarSpring.business.abstracts;

import java.util.List;


import com.etiya.rentACarSpring.business.dtos.UserSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateUserRequest;
import com.etiya.rentACarSpring.business.requests.DeleteUserRequest;
import com.etiya.rentACarSpring.business.requests.UpdateUserRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.entities.User;


public interface UserService {
	Result add(CreateUserRequest createUserRequest);
	Result delete(DeleteUserRequest deleteUserRequest);
	Result update(UpdateUserRequest updateUserRequest);

	DataResult<List<UserSearchListDto>> getAll();
    Result existsByEmail(String email);
    DataResult<List<UserSearchListDto>> getByEmail(String email);
    DataResult<UserSearchListDto> getById(int id);
    DataResult<User> getByUser(int id);

    Result existsByUserId(int id);
    
	

}
