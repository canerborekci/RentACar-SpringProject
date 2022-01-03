package com.etiya.rentACarSpring.business.requests.authantication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorporateCustomerRegisterRequest {
    @JsonIgnore
    private int id;

    @NotNull
    @ApiModelProperty(example = "Etiya")
    private String companyName;

    @NotNull
    @NotBlank
    private String taxNumber;


    @NotNull
    @NotBlank
    @Email
    @ApiModelProperty(example = "etiya@etiya.com")
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 20,min = 8)
    private String password;


}
