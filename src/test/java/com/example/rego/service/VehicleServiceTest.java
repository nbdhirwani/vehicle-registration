package com.example.rego.service;

import com.example.rego.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class VehicleServiceTest {

    private static final String PLATE_NUMBER = "plateNumber";

    @Autowired
    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        final Vehicle vehicleToAdd = Vehicle.builder().plateNumber(PLATE_NUMBER).build();
        vehicleService.add(vehicleToAdd);
    }

    @Test
    void test_add() {
        final Vehicle vehicleToAdd = Vehicle.builder().build();
        final Vehicle addedVehicle = vehicleService.add(vehicleToAdd);
        assertNotNull(addedVehicle);
    }

    @Test
    void test_get() {
        final Vehicle vehicle = vehicleService.get(PLATE_NUMBER);
        assertNotNull(vehicle);
        assertThat(vehicle.getPlateNumber(), is(PLATE_NUMBER));
    }

    @Test
    void test_getAll() {
        final Collection<Vehicle> all = vehicleService.getAll();
        assertNotNull(all);
        assertThat(all, is(not(empty())));
    }
}