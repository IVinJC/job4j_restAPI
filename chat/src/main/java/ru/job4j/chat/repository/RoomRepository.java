package ru.job4j.chat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.chat.model.Room;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    Room findByName(String name);

    List<Room> findAll();

    Room findById(int id);

    Room findByUser(String user);
}
