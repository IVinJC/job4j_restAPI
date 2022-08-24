package ru.job4j.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.services.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    private final ObjectMapper objectMapper;
    private BCryptPasswordEncoder encoder;

    public PersonController(PersonService personService, ObjectMapper objectMapper, BCryptPasswordEncoder encoder) {
        this.personService = personService;
        this.objectMapper = objectMapper;
        this.encoder = encoder;
    }

    @PostMapping("/reg")
    public void signUp(@RequestBody Person person) {
        if (person.getPassword() == null) {
            throw new IllegalArgumentException("Define username and password");
        }
        person.setPassword(encoder.encode(person.getPassword()));
        personService.create(person);
    }

    @GetMapping("/")
    public List<Person> findAll() {
        return personService.getPeople();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        var person = this.personService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Person is not found. Please, check requisites."
        ));
        return new ResponseEntity<>(person,
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        String name = person.getName();
        String password = person.getPassword();
        if (name == null || password == null) {
            throw new IllegalArgumentException("Define username and password");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return new ResponseEntity<>(
                this.personService.create(person),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        String name = person.getName();
        String password = person.getPassword();
        if (name == null || password == null) {
            throw new IllegalArgumentException("Define username and password");
        }
        if (password.length() < 5) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        this.personService.create(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.personService.delete(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        log.error(e.getLocalizedMessage());
    }

}
