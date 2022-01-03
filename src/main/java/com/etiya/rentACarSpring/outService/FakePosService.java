package com.etiya.rentACarSpring.outService;


import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessResult;

public class FakePosService {
    public boolean fakePos(String cardNumber, String cardHolderName, String expirationDate, String cvv, double price) {
        double limit = 1000;
        if (price <= limit) {
            return true;
        }
        return false;
    }
}
