package com.etiya.rentACarSpring.core.utilities.adapters;

import com.etiya.rentACarSpring.business.requests.posService.PosServiceRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.outService.FakeFindeksService;
import com.etiya.rentACarSpring.outService.FakePosService;
import org.springframework.stereotype.Service;

@Service
public class FakePosServiceAdapter implements PaymentService{
    FakePosService fakePosService=new FakePosService();

    @Override
    public boolean withdraw(PosServiceRequest fakePosServiceRequest) {
        return this.fakePosService.fakePos(fakePosServiceRequest.getCardNumber(),
                fakePosServiceRequest.getCardHolderName(), fakePosServiceRequest.getExpirationDate(),
                fakePosServiceRequest.getCvv(), fakePosServiceRequest.getTotalPrice());
    }
}
