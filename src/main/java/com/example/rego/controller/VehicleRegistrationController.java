package com.example.rego.controller;

import com.example.rego.model.Person;
import com.example.rego.model.Vehicle;
import com.example.rego.service.VehicleRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class VehicleRegistrationController {

    @Autowired
    private final VehicleRegistrationService vehicleRegistrationService;

    @PostMapping("/person/{personId}/vehicle/{plateNumber}")
    public ResponseEntity linkPersonToVehicle(@PathVariable final String personId, @PathVariable final String plateNumber) {
        try {
            vehicleRegistrationService.linkPersonToVehicle(personId, plateNumber);
            return ResponseEntity.ok().build();
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().body(re.getMessage());
        }
    }

    @DeleteMapping("/{plateNumber}")
    public ResponseEntity unlinkPersonFromVehicle(@PathVariable("plateNumber") final String plateNumber) {
        try {
            vehicleRegistrationService.unlinkPersonFromVehicle(plateNumber);
            return ResponseEntity.ok().build();
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().body(re.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Map<Vehicle, Person>> getALL() {
        return ResponseEntity.ok(vehicleRegistrationService.getAll());
    }
}
