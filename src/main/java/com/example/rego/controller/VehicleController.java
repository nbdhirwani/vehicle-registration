package com.example.rego.controller;

import com.example.rego.model.Vehicle;
import com.example.rego.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/vehicle")
@AllArgsConstructor
public class VehicleController {

    @Autowired
    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody final Vehicle vehicle) {
        final Vehicle addedVehicle = vehicleService.add(vehicle);
        return ResponseEntity.ok(addedVehicle);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Vehicle>> getALL() {
        return ResponseEntity.ok(vehicleService.getAll());
    }
}
