package ru.job4j.chat.repository;

import ru.job4j.chat.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findAll();
    Optional<Person> findByName(String username);
}
