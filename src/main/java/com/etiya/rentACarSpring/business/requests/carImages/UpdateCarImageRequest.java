package com.etiya.rentACarSpring.business.requests.carImages;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarImageRequest {
	private int id;


    private int carId;
    
    private String imagePath;

    private Date imageDate;

    @JsonIgnore
    private MultipartFile file;
}
