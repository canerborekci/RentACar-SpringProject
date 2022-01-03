package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.business.dtos.UserSearchListDto;
import com.etiya.rentACarSpring.business.requests.CreateUserRequest;
import com.etiya.rentACarSpring.business.requests.DeleteUserRequest;
import com.etiya.rentACarSpring.business.requests.UpdateUserRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.UserDao;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.User;

@Service
public class UserManager implements UserService {
	private UserDao userDao;
	private ModelMapperService modelMapperService;
	private LanguageWordService languageWordService;

	@Autowired
	public UserManager(UserDao userDao, ModelMapperService modelMapperService,LanguageWordService languageWordService) {
		super();
		this.userDao = userDao;
		this.modelMapperService = modelMapperService;
		this.languageWordService=languageWordService;
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) {
		User user = modelMapperService.forRequest().map(createUserRequest, User.class);
		this.userDao.save(user);
		return new SuccessResult(this.languageWordService.getValueByKey(Messages.CUSTOMERADD).getData());
	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		User user = modelMapperService.forRequest().map(deleteUserRequest, User.class);
		this.userDao.delete(user);
		return new SuccessResult(this.languageWordService.getValueByKey(Messages.CUSTOMERDELETE).getData());
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User user = modelMapperService.forRequest().map(updateUserRequest, User.class);
		this.userDao.save(user);
		return new SuccessResult(this.languageWordService.getValueByKey(Messages.CUSTOMERUPDATE).getData());
	}

	@Override
	public DataResult<List<UserSearchListDto>> getAll() {
		List<User> result = this.userDao.findAll();
		List<UserSearchListDto> response = result.stream()
				.map(user -> modelMapperService.forDto().map(user, UserSearchListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<UserSearchListDto>>(response,this.languageWordService.getValueByKey(Messages.CUSTOMERLIST).getData());
	}

	@Override
	public Result existsByEmail(String email) {

		if (this.userDao.existsByEmail(email)) {
			return new ErrorResult(this.languageWordService.getValueByKey(Messages.EMAILERROR).getData());
		}
		return new SuccessResult(this.languageWordService.getValueByKey(Messages.CUSTOMERISALREADYEXISTS).getData());
	}

	@Override
	public DataResult<List<UserSearchListDto>> getByEmail(String email) {
		List<User> result = this.userDao.getByEmail(email);
		List<UserSearchListDto> response = result.stream()
				.map(user -> modelMapperService.forDto().map(user, UserSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<UserSearchListDto>>(response,this.languageWordService.getValueByKey(Messages.CUSTOMERFOUND).getData());

	}

	@Override
	public DataResult<UserSearchListDto> getById(int id) {
		User user = this.userDao.findById(id).get();
		if (user != null) {
			UserSearchListDto response = modelMapperService.forDto().map(user, UserSearchListDto.class);
			return new SuccessDataResult<UserSearchListDto>(response,this.languageWordService.getValueByKey(Messages.CUSTOMERFOUND).getData());
		}
		return null;
	}

	@Override
	public Result existsByUserId(int id) {
		boolean result = this.userDao.existsById(id);
		if (result == false) {
			return new ErrorResult(this.languageWordService.getValueByKey(Messages.CUSTOMERNOTFOUND).getData());
		}
		return new SuccessResult();
	}


	@Override
	public DataResult<User> getByUser(int id) {
		return new SuccessDataResult<User>(this.userDao.getById(id),
				this.languageWordService.getValueByKey(Messages.CUSTOMERFOUND).getData());
	}

}
