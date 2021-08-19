package com.example.rego.service;

import com.example.rego.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class VehicleService {

    private static Map<String, Vehicle> dataStore;
    static {
        dataStore = new HashMap<>();
    }

    public Vehicle add(final Vehicle vehicleToAdd) {
        dataStore.put(vehicleToAdd.getPlateNumber(), vehicleToAdd);
        return vehicleToAdd;
    }

    public Vehicle get(final String plateNumber) {
        return dataStore.get(plateNumber);
    }

    public Collection<Vehicle> getAll() {
        return dataStore.values();
    }
}
