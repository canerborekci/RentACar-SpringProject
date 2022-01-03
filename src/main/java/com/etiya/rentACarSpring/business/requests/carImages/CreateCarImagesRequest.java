package com.etiya.rentACarSpring.business.requests.carImages;

import java.util.Date;



import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarImagesRequest {
	
	private int id;

    private int carId;

    @JsonIgnore
    private String imagePath;

    @JsonIgnore
    private Date imageDate;

    @JsonIgnore
    private MultipartFile file;
}
