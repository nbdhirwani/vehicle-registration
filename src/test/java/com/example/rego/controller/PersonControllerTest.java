package com.example.rego.controller;

import com.example.rego.model.Person;
import com.example.rego.service.PersonService;
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
class PersonControllerTest {

    private static String ROOT_URL;

    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        ROOT_URL = "/person";
    }

    @Test
    void test_createPerson() throws Exception {
        final Person person = Person.builder().firstName("firstName").lastName("lastName").build();
        final Person personResponse = Person.builder().id("id").firstName("firstName").lastName("lastName").build();
        when(personService.add(any(Person.class))).thenReturn(personResponse);

        final String personAsString = objectMapper.writeValueAsString(person);
        final String personResponseAsString = objectMapper.writeValueAsString(personResponse);

        mockMvc.perform(post(ROOT_URL).content(personAsString).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(personResponseAsString));
    }

    @Test
    void test_getAll() throws Exception {
        final Person person = Person.builder().id("id").firstName("firstName").lastName("lastName").build();
        final List<Person> people = Collections.singletonList(person);
        when(personService.getAll()).thenReturn(people);

        final String peopleAsString = objectMapper.writeValueAsString(people);

        mockMvc.perform(get(ROOT_URL.concat("/all")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(peopleAsString));
    }
}