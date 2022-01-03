package com.etiya.rentACarSpring.outService;

import java.util.Random;

public class FakeFindeksService {
    Random rand=new Random();

    public Integer getFindeksScore(){
        int findeks = rand.nextInt(1900);
        return findeks;
    }
}
