package ru.job4j.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.dto.PersonDTO;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.services.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    private final ObjectMapper objectMapper;
    private BCryptPasswordEncoder encoder;
    private final ModelMapper modelMapper;

    public PersonController(PersonService personService, ObjectMapper objectMapper,
                            BCryptPasswordEncoder encoder, ModelMapper modelMapper) {
        this.personService = personService;
        this.objectMapper = objectMapper;
        this.encoder = encoder;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/reg")
    public ResponseEntity<HashMap<String, String>> signUp(@RequestBody PersonDTO personDTO) {
        if (personDTO.getPassword() == null) {
            throw new IllegalArgumentException("Define username and password");
        }
        personDTO.setPassword(encoder.encode(toPerson(personDTO).getPassword()));
        personService.create(toPerson(personDTO));
        return ResponseEntity.status(HttpStatus.OK).header("person", "created")
                .contentType(MediaType.ALL).body(new HashMap<>(Map.of(
                        toPerson(personDTO).getName(), toPerson(personDTO).getPassword())));
    }

    @GetMapping("/")
    public List<PersonDTO> findAll() {
        return personService.getPeople()
                .stream()
                .map(this::toPersonDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        var person = this.personService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "PersonDTO is not found. Please, check requisites."
        ));
        return new ResponseEntity<>(person,
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody PersonDTO personDTO) {
        String name = personDTO.getName();
        String password = personDTO.getPassword();
        if (name == null || password == null) {
            throw new IllegalArgumentException("Define username and password");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return new ResponseEntity<>(
                this.personService.create(toPerson(personDTO)),
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

    private Person toPerson(PersonDTO personDTO) {
       return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO toPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }
}
