package com.etiya.rentACarSpring.entities.complexTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersInvoice {
    private int userId;
    private String invoiceNo;
    private double totalPrice;
    private int rentalId;
}
