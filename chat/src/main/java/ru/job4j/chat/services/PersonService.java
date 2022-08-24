package ru.job4j.chat.services;

import ru.job4j.chat.model.Person;
import ru.job4j.chat.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    public Optional<Person> findByName(String username) {
        return personRepository.findByName(username);
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person update(Person person, int id) {
        person.setId(id);
        return personRepository.save(person);
    }

    public void delete(int id) {
        Person role = new Person();
        role.setId(id);
        personRepository.delete(role);
    }
}
