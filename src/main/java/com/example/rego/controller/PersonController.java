package com.example.rego.controller;

import com.example.rego.model.Person;
import com.example.rego.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    @Autowired
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody final Person person) {
        final Person personAdded = personService.add(person);
        return ResponseEntity.ok(personAdded);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getALL() {
        return ResponseEntity.ok(personService.getAll());
    }
}
