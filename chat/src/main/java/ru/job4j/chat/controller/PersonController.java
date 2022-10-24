package ru.job4j.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.PersonDTO;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.services.PersonService;
import ru.job4j.chat.services.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final RoomService roomService;
    private final BCryptPasswordEncoder encoder;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;


    @PostMapping(value = "/reg")
    public ResponseEntity<HashMap<String, String>> signUp(@RequestBody @Valid PersonDTO personDTO) {
        if (personDTO.getPassword() == null) {
            throw new IllegalArgumentException("Define username and password");
        }
        personDTO.setPassword(encoder.encode(personDTO.getPassword()));
        personService.create(personDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .header("person", "created")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HashMap<>(Map.of(
                        personDTO.getName(), personDTO.getPassword())));
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonDTO>> findAll() {
        return new ResponseEntity<>(personService.getPeople(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable @Valid int id) {
        PersonDTO personDTO = personService.findById(id);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody @Valid PersonDTO personDTO) {
        String name = personDTO.getName();
        String password = personDTO.getPassword();
        if (name == null || password == null) {
            throw new IllegalArgumentException("Define username and password");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return new ResponseEntity<>(
                this.personService.create(personDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid PersonDTO personDTO) {
        String name = personDTO.getName();
        String password = personDTO.getPassword();
        if (name == null || password == null) {
            throw new IllegalArgumentException("Define username and password");
        }
        if (password.length() < 5) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        this.personService.create(personDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.personService.delete(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
        log.error(e.getLocalizedMessage());
    }
}
