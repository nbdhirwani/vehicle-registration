package com.example.rego.service;

import com.example.rego.model.Person;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private static Map<String, Person> dataStore;
    static {
        dataStore = new HashMap<>();
    }

    public Person add(final Person personToAdd) {
        final String id = UUID.randomUUID().toString();
        personToAdd.setId(id);
        dataStore.put(id, personToAdd);
        return personToAdd;
    }

    public Person get(final String id) {
        return dataStore.get(id);
    }

    public List<Person> getAll() {
        return dataStore.values().stream().collect(Collectors.toList());
    }
}
