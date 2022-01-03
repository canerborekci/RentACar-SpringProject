package com.etiya.rentACarSpring.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersInvoicesDto {
    private int userId;
    private String invoiceNo;
    private double totalPrice;
    private int rentalId;
}
