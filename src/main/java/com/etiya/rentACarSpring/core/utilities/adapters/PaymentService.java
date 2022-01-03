package com.etiya.rentACarSpring.core.utilities.adapters;

import com.etiya.rentACarSpring.business.requests.posService.PosServiceRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;

public interface PaymentService {
    public boolean withdraw(PosServiceRequest fakePosServiceRequest);
}
