package com.etiya.rentACarSpring;

import java.util.HashMap;
import java.util.Map;

import com.etiya.rentACarSpring.business.constants.Messages;
import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorResult;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorDataResult;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestControllerAdvice
public class RentACarSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarSpringApplication.class, args);
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.etiya.rentACarSpring"))
          .build();
    }
	
	@Bean
	ModelMapper getModelMapper() {		
		return new ModelMapper();
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception){
		Map<String, String> validationErrors = new HashMap<String,String>();

		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> error = new ErrorDataResult<Object>(validationErrors,"Validation Errors");
		return error;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult HttpMessageNotReadableException(HttpMessageNotReadableException exception){

		ErrorResult error = new ErrorResult(Messages.FORMATERROR);
		return error;
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult DataIntegrityViolationException(DataIntegrityViolationException exception){

		ErrorResult error = new ErrorResult(Messages.FOREIGNKEYERROR);
		return error;
	}



}
