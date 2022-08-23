package ru.job4j.services;

import ru.job4j.model.Person;
import org.springframework.stereotype.Service;
import ru.job4j.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getRoles() {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
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
