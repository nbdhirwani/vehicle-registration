package com.example.rego.controller;

import com.example.rego.model.Vehicle;
import com.example.rego.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerTest {

    private static String ROOT_URL;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        ROOT_URL = "/vehicle";
    }

    @Test
    void test_createVehicle() throws Exception {
        final Vehicle vehicle = Vehicle.builder()
                .plateNumber("plateNumber")
                .make("make")
                .model("model")
                .type("type")
                .manufacturer("manufacturer")
                .year("year")
                .build();
        when(vehicleService.add(any(Vehicle.class))).thenReturn(vehicle);

        final String vehicleAsString = objectMapper.writeValueAsString(vehicle);

        mockMvc.perform(post(ROOT_URL).content(vehicleAsString).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(vehicleAsString));
    }

    @Test
    void test_getAll() throws Exception {
        final Vehicle vehicle = Vehicle.builder()
                .plateNumber("plateNumber")
                .make("make")
                .model("model")
                .type("type")
                .manufacturer("manufacturer")
                .year("year")
                .build();
        final List<Vehicle> vehicles = Collections.singletonList(vehicle);
        when(vehicleService.getAll()).thenReturn(vehicles);

        final String vehiclesAsString = objectMapper.writeValueAsString(vehicles);

        mockMvc.perform(get(ROOT_URL.concat("/all")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(vehiclesAsString));
    }
}