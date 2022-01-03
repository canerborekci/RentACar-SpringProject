package com.etiya.rentACarSpring.core.utilities.mapping.results;

public class SuccessResult extends Result {
	public SuccessResult() {
		super(true);
	}
	public SuccessResult(String message) {
		super(true,message);
	}

}
