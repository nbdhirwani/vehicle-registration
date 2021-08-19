package com.example.rego.service;

import com.example.rego.model.Person;
import com.example.rego.model.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class VehicleRegistrationService {

    private static Map<Vehicle, Person> dataStore;
    static {
        dataStore = new HashMap<>();
    }

    @Autowired
    private final PersonService personService;

    @Autowired
    private final VehicleService vehicleService;

    public void linkPersonToVehicle(final String personId, final String plateNumber) throws RuntimeException {
        final Person person = personService.get(personId);
        final Vehicle vehicle = vehicleService.get(plateNumber);

        if(isNull(person) || isNull(vehicle)) {
            throw new RuntimeException("Either person or vehicle does not exist in database.");
        } else if(dataStore.containsKey(vehicle)) {
            throw new RuntimeException("Plate number already linked to another person.");
        }
        dataStore.put(vehicle, person);
    }

    public void unlinkPersonFromVehicle(final String plateNumber) throws RuntimeException {
        final Vehicle vehicle = vehicleService.get(plateNumber);
        if(isNull(vehicle)) {
            throw new RuntimeException("Vehicle does not exist in database.");
        }
        dataStore.remove(vehicle);
    }

    public Map<Vehicle, Person> getAll() {
        return dataStore;
    }
}
