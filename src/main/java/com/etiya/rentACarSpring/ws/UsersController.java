package com.etiya.rentACarSpring.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.dtos.UserSearchListDto;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;

@RestController
@RequestMapping("api/users")
public class UsersController {
	private UserService userService;
    
	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
//	@PostMapping("add")
//	public Result add(@RequestBody @Valid CreateUserRequest createUserRequest) {
//		this.userService.add(createUserRequest);
//		return new SuccessResult();
//	}
	@GetMapping("all")
	public DataResult<List<UserSearchListDto>> getAll(){
		return this.userService.getAll();
		
	}
//	@PutMapping("update")
//	public Result update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
//		this.userService.update(updateUserRequest);
//		return new SuccessResult();
//	}
//	@DeleteMapping("delete")
//	public Result delete(@RequestBody @Valid DeleteUserRequest deleteUserRequest) {
//		this.userService.delete(deleteUserRequest);
//		return new SuccessResult();
//	}


}
