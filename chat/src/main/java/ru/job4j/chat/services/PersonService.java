package ru.job4j.chat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.chat.dto.PersonDTO;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.repository.PersonRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<PersonDTO> getPeople() {
        return personRepository.findAll().stream()
                .map(person -> PersonDTO.builder()
                        .name(person.getName())
                        .password(person.getPassword())
                        .room(person.getRoom()).build())
                .collect(Collectors.toList());
    }

    public PersonDTO findById(int id) {
        return Stream.of(personRepository.findById(id))
                .map(entity -> PersonDTO.builder()
                        .name(entity.getName())
                        .password(entity.getPassword())
                        .room(entity.getRoom())
                        .build()).findAny()
                .orElseThrow(() -> new NoSuchElementException("No User with id =" + id));
    }

    public PersonDTO findByName(String username) {
        return Stream.of(personRepository.findByName(username))
                .map(entity -> PersonDTO.builder()
                        .name(entity.getName())
                        .password(entity.getPassword())
                        .room(entity.getRoom())
                        .build()).findAny()
                .orElseThrow(() -> new NoSuchElementException("No User with Name =" + username));
    }

    public Person create(PersonDTO personDTO) {
        Person person = new Person(personDTO.getName(), personDTO.getPassword(), personDTO.getRoom());
        return personRepository.save(person);
    }

    public Person update(PersonDTO personDTO, int id) {
        Person person = new Person(personDTO.getName(), personDTO.getPassword(), personDTO.getRoom());
        person.setId(id);
        return personRepository.save(person);
    }

    public void delete(int id) {
        Person person = new Person();
        person.setId(id);
        personRepository.delete(person);
    }
}
