package com.example.rego.controller;

import com.example.rego.model.Person;
import com.example.rego.model.Vehicle;
import com.example.rego.service.VehicleRegistrationService;
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
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleRegistrationControllerTest {

    private static String ROOT_URL;

    @MockBean
    private VehicleRegistrationService vehicleRegistrationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        ROOT_URL = "/registration";
    }

    @Test
    void test_linkPersonToVehicle_returns_bad_request() throws Exception {
        doThrow(RuntimeException.class).when(vehicleRegistrationService).linkPersonToVehicle("personId", "plateNumber");
        mockMvc.perform(post(ROOT_URL.concat("/person/personId/vehicle/plateNumber")))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_linkPersonToVehicle_returns_ok() throws Exception {
        doNothing().when(vehicleRegistrationService).linkPersonToVehicle(anyString(), anyString());
        mockMvc.perform(post(ROOT_URL.concat("/person/personId/vehicle/plateNumber")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void test_unlinkPersonFromVehicle_returns_bad_request() throws Exception {
        doThrow(RuntimeException.class).when(vehicleRegistrationService).unlinkPersonFromVehicle(anyString());
        mockMvc.perform(delete(ROOT_URL.concat("/plateNumber")))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_unlinkPersonFromVehicle_returns_ok() throws Exception {
        doNothing().when(vehicleRegistrationService).unlinkPersonFromVehicle(anyString());
        mockMvc.perform(delete(ROOT_URL.concat("/plateNumber")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void test_getALL() throws Exception {
        final Vehicle vehicle = Vehicle.builder()
                .plateNumber("plateNumber")
                .make("make")
                .model("model")
                .type("type")
                .manufacturer("manufacturer")
                .year("year")
                .build();
        final Person person = Person.builder().id("id").firstName("firstName").lastName("lastName").build();
        final Map<Vehicle, Person> registrations = Collections.singletonMap(vehicle, person);
        when(vehicleRegistrationService.getAll()).thenReturn(registrations);

        final String registrationsAsString = objectMapper.writeValueAsString(registrations);

        mockMvc.perform(get(ROOT_URL.concat("/all")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(registrationsAsString));
    }
}