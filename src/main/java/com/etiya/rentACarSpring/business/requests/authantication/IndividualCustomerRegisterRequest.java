package com.etiya.rentACarSpring.business.requests.authantication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualCustomerRegisterRequest {
    @JsonIgnore
    private int id;

    @NotNull
    @Size(max = 25)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String lastName;

    @NotNull
    @ApiModelProperty(example = "1985-01-01")
    private LocalDate birthDay;

    @NotNull
    @NotBlank
    @Email
    @ApiModelProperty(example = "ad.soyad@etiya.com")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;


}
