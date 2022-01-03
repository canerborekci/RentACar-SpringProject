package com.etiya.rentACarSpring.core.utilities.adapters;

import com.etiya.rentACarSpring.business.abstracts.CarService;
import com.etiya.rentACarSpring.business.abstracts.RentalService;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Service;




@Service
public class Pricing implements PricingService {
    private RentalService rentalService;
    private CarService carService;

    @Override
    public double invoicePricing(int carId, int rentalId) {
      int countOfDays = this.rentalService.getCountOfRentalDays(rentalId).getData();
      double dailyPrice = this.carService.getById(carId).getData().getDailyPrice();
        return countOfDays * dailyPrice;

    }

}