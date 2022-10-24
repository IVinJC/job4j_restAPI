package ru.job4j.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.chat.dto.PersonDTO;
import ru.job4j.chat.dto.RoomDTO;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.services.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RestTemplate restTemplate;
    private final RoomService roomService;
    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";

    @GetMapping("/message")
    public ResponseEntity<Message> showUser(
            HttpServletRequest request, @RequestParam(value = "name", required = false) String name) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("Authorization", request.getHeader("Authorization"));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        List<PersonDTO> personDTOS = restTemplate.exchange(API, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<PersonDTO>>() {
                }).getBody();
        Message message = null;
        if (personDTOS != null) {
            PersonDTO personDTO = personDTOS.stream()
                    .filter(p -> name.equals(p.getName()))
                    .findFirst()
                    .orElseThrow(
                            () -> new IllegalArgumentException("Who are you? You must register or enter the Room")
                    );
            message = new Message("Hello " + personDTO.getName());
        }
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @PostMapping("/message/{id}/{user}")
    public ResponseEntity<HashMap<String, String>> sendMessage(@PathVariable("id") int id, @PathVariable("user") String user,
            @RequestParam(value = "message") String message) {
        RoomDTO roomDTO = roomService.findById(id);
        if (roomDTO == null) {
            throw new NoSuchElementException("No such room where id = " + id);
        }
        roomDTO.setUser(user);
        roomDTO.setMessage(message);
        roomService.update(roomDTO, id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("message", "has sent")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HashMap<>(Map.of(
                        "Message has Sent to", roomDTO.getUser())));
    }

    @GetMapping("/message/get")
    public ResponseEntity<HashMap<String, String>> receive(@RequestParam("user") String user) {
        RoomDTO roomDTO = roomService.findByUser(user);
        if (roomDTO == null) {
            throw new NoSuchElementException("This Name" + user + " has not received message yet");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HashMap<>(Map.of(
                        "Your message is", roomDTO.getMessage())));
    }

    @PostMapping("/add")
    public ResponseEntity<Room> add(@RequestBody @Valid RoomDTO roomDTO) {
        return new ResponseEntity<>(roomService.create(roomDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delRoom(@PathVariable("id") int id) {
        roomService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoomDTO>> findAllRooms() {
        List<RoomDTO> all = roomService.findAll();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(all);
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody PersonDTO personDTO) {
        Person rsl = restTemplate.postForObject(API, personDTO, Person.class);
        return new ResponseEntity<>(rsl, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody PersonDTO personDTO) {
        restTemplate.put(API, personDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        restTemplate.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }
}
