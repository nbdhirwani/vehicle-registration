package com.example.rego.service;

import com.example.rego.model.Person;
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
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    private String idForTest;

    @BeforeEach
    void setUp() {
        final Person personToAdd = Person.builder().build();
        final Person addedPerson = personService.add(personToAdd);
        idForTest = addedPerson.getId();
    }

    @Test
    void test_add() {
        final Person personToAdd = Person.builder().build();
        final Person addedPerson = personService.add(personToAdd);
        assertNotNull(addedPerson);
        assertNotNull(addedPerson.getId());
    }

    @Test
    void test_get() {
        final Person person = personService.get(idForTest);
        assertNotNull(person);
        assertThat(person.getId(), is(idForTest));
    }

    @Test
    void test_getAll() {
        final Collection<Person> all = personService.getAll();
        assertNotNull(all);
        assertThat(all, is(not(empty())));
    }
}