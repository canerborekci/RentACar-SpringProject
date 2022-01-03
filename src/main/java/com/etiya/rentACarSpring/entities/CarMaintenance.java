package com.etiya.rentACarSpring.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_maintenance")
public class CarMaintenance {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_maintenance_id")
    private int carMaintenanceId;

    @Column(name = "description")
    private String description;

    @Column(name = "rent_date")
    private LocalDate maintenanceDate;

    @Column(name = "expected_return_date")
    private LocalDate expectedReturnDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

}
