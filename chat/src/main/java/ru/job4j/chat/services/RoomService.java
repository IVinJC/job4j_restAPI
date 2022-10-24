package ru.job4j.chat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.chat.dto.RoomDTO;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repository.RoomRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Room create(RoomDTO roomDTO) {
        Room room = new Room(roomDTO.getName());
        return roomRepository.save(room);
    }

    public RoomDTO findById(int id) {
        return Stream.of(roomRepository.findById(id))
                .map(room -> RoomDTO.builder()
                        .name(room.getName())
                        .user(room.getUser())
                        .message(room.getMessage())
                        .build())
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Could not find Room where id = " + id));
    }

    public Room update(RoomDTO roomDTO, int id) {
        Room room = new Room(roomDTO.getName());
        room.setUser(roomDTO.getUser());
        room.setMessage(roomDTO.getMessage());
        room.setId(id);
        return roomRepository.save(room);
    }

    public RoomDTO findByUser(String user) {
        return Stream.of(roomRepository.findByUser(user))
                .map(room -> RoomDTO.builder()
                        .name(room.getName())
                        .user(room.getUser())
                        .message(room.getMessage())
                        .build())
                .findAny().orElseThrow(() -> new NoSuchElementException("Could not find " + user));
    }

    public void delete(int id) {
        Room room = new Room();
        room.setId(id);
        roomRepository.delete(room);
    }

    public RoomDTO findByName(String name) {
        return Stream.of(roomRepository.findByName(name))
                .map(room -> RoomDTO.builder()
                        .name(room.getName())
                        .user(room.getUser())
                        .message(room.getMessage())
                        .build())
                .findAny().orElseThrow(() -> new NoSuchElementException("Could not find " + name));
    }

    public List<RoomDTO> findAll() {
        return roomRepository.findAll().stream()
                .map(entity -> RoomDTO.builder()
                        .name(entity.getName())
                        .user(entity.getUser())
                        .message(entity.getMessage())
                        .build())
                .collect(Collectors.toList());
    }
}
