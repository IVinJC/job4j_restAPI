package ru.job4j.chat.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RestTemplate restTemplate;
    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";

    public RoomController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/message")
    public ResponseEntity<Message> show(@RequestBody Person person,
                                        @RequestParam String name) {
        Message message = name.equals("Jack") ? new Message("Hello " + person.getName()) : new Message("Who are you?");
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping({"/", ""})
    public List<Room> findAll() {
        List<Room> rsl = new ArrayList<>();
        List<Person> rooms = restTemplate.exchange(API, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Person>>() {
                }).getBody();
        assert rooms != null;
        Room room = new Room(
                1, "Комната №1", new ArrayList<>(Arrays.asList(rooms.get(0), rooms.get(1))));
        rsl.add(room);
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
