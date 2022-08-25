package ru.job4j.chat.model;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Room {
    @NotNull(message = "ID must be not null")
    private int id;
    @NotNull(message = "Name must be not null")
    private String name;
    private List<Person> persons;

    public Room(int id, String name, List<Person> persons) {
        this.id = id;
        this.name = name;
        this.persons = persons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoomDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
