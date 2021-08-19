package com.example.rego.service;

import com.example.rego.model.Person;
import com.example.rego.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class VehicleRegistrationServiceTest {

    @MockBean
    private PersonService personService;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private VehicleRegistrationService vehicleRegistrationService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void test_linkPersonToVehicle_throws_RuntimeException_when_person_is_missing_in_database() {
        when(personService.get(anyString())).thenReturn(null);

        final Vehicle vehicle = Vehicle.builder().plateNumber("plateNumber").build();
        when(vehicleService.get(anyString())).thenReturn(vehicle);

        assertThrows(RuntimeException.class, () -> {
            vehicleRegistrationService.linkPersonToVehicle("id", "plateNumber");
        });
    }

    @Test
    void test_linkPersonToVehicle_throws_RuntimeException_when_vehicle_is_missing_in_database() {
        final Person person = Person.builder().id("id").build();
        when(personService.get(anyString())).thenReturn(person);

        when(vehicleService.get(anyString())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            vehicleRegistrationService.linkPersonToVehicle("id", "plateNumber");
        });
    }

    @Test
    void test_linkPersonToVehicle_throws_RuntimeException_when_vehicle_is_already_linked() {
        final Person person1 = Person.builder().id("id1").build();
        when(personService.get(anyString())).thenReturn(person1);
        final Person person2 = Person.builder().id("id2").build();
        when(personService.get(anyString())).thenReturn(person2);

        final Vehicle vehicle = Vehicle.builder().plateNumber("plateNumber1").build();
        when(vehicleService.get(anyString())).thenReturn(vehicle);

        vehicleRegistrationService.linkPersonToVehicle("id1", "plateNumber1");
        assertThrows(RuntimeException.class, () -> {
            vehicleRegistrationService.linkPersonToVehicle("id2", "plateNumber1");
        });
    }

    @Test
    void test_linkPersonToVehicle_is_success() {
        final Person person = Person.builder().id("id").build();
        when(personService.get(anyString())).thenReturn(person);

        final Vehicle vehicle = Vehicle.builder().plateNumber("plateNumber").build();
        when(vehicleService.get(anyString())).thenReturn(vehicle);

        vehicleRegistrationService.linkPersonToVehicle("id", "plateNumber");

        verify(personService).get("id");
        verify(vehicleService).get("plateNumber");
    }

    @Test
    void test_unlinkPersonFromVehicle_throws_RuntimeException_when_vehicle_missing_in_database() {
        when(vehicleService.get(anyString())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            vehicleRegistrationService.unlinkPersonFromVehicle("plateNumber");
        });
    }

    @Test
    void test_unlinkPersonFromVehicle_is_success() {
        final Vehicle vehicle = Vehicle.builder().plateNumber("plateNumber").build();
        when(vehicleService.get(anyString())).thenReturn(vehicle);

        vehicleRegistrationService.unlinkPersonFromVehicle("plateNumber");

        verify(vehicleService).get("plateNumber");
    }
}