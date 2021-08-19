package com.example.rego;

import com.example.rego.controller.PersonController;
import com.example.rego.controller.VehicleController;
import com.example.rego.controller.VehicleRegistrationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class VehicleRegistrationApplicationTests {

	@Autowired
	private PersonController personController;

	@Autowired
	private VehicleController vehicleController;

	@Autowired
	private VehicleRegistrationController vehicleRegistrationController;

	@Test
	void contextLoads() {
		assertNotNull(personController);
		assertNotNull(vehicleController);
		assertNotNull(vehicleRegistrationController);
	}

}
