package com.etiya.rentACarSpring.business.dtos;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardInformationDto {
    @NotBlank
    @NotNull
    @Size(max = 25)
    private String cardName;

    @NotBlank
    @NotNull
    @Size(min = 16, max = 16)
    private String cardNumber;

    @NotBlank
    @NotNull
    @Size(max = 25)
    private String cardHolderName;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 5)
    private String expirationDate;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 3)
    private String cvv;
}
