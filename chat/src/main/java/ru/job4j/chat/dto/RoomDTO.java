package ru.job4j.chat.dto;

import java.util.List;

public class RoomDTO {
    private String name;
    private List<PersonDTO> personDTOS;

    public RoomDTO(String name, List<PersonDTO> personDTOS) {
        this.name = name;
        this.personDTOS = personDTOS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PersonDTO> getPersons() {
        return personDTOS;
    }

    public void setPersons(List<PersonDTO> personDTOS) {
        this.personDTOS = personDTOS;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoomDTO{");
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
