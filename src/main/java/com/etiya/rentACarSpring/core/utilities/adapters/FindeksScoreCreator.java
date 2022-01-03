package com.etiya.rentACarSpring.core.utilities.adapters;

import java.util.Random;


import com.etiya.rentACarSpring.outService.FakeFindeksService;
import org.springframework.stereotype.Service;
@Service
public class FindeksScoreCreator implements FindeksScoreCreatorService {
	
    FakeFindeksService fakeFindeksService=new FakeFindeksService();
	

	public Integer FindeksScoreCreator() {

		return  fakeFindeksService.getFindeksScore();
	}
}
