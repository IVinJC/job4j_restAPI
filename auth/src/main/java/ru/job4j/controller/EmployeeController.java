package ru.job4j.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final RestTemplate restTemplate;
    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";

    public EmployeeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        List<Employee> rsl = new ArrayList<>();
        /**
         * Searching by ID of Person
         */
        /**Person person1 = restTemplate.exchange(
         API_ID,
         HttpMethod.GET, null, new ParameterizedTypeReference<Person>() { }, 1
         ).getBody();
         Person person3 = restTemplate.exchange(
         API_ID,
         HttpMethod.GET, null, new ParameterizedTypeReference<Person>() { }, 2
         ).getBody();*/
        List<Person> persons = restTemplate.exchange(API, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Person>>() {
                }).getBody();
        Employee employee1 = new Employee(
                1, "Vadim", "Dedeyko",
                236696, LocalDate.of(2021, 11, 06),
                new ArrayList<>(Arrays.asList(persons.get(0), persons.get(1))));
        Employee employee2 = new Employee(
                1, "Sergey", "Petrov",
                2366, LocalDate.of(2021, 12, 20),
                new ArrayList<>(Arrays.asList(persons.get(2), persons.get(3))));
        rsl.add(employee1);
        rsl.add(employee2);
        return rsl;
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person rsl = restTemplate.postForObject(API, person, Person.class);
        return new ResponseEntity<>(rsl, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        restTemplate.put(API, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        restTemplate.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }
}
