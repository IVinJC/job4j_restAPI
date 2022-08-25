package ru.job4j.chat.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;
public class RoleDTO {
    @NotNull(message = "Name must be not null")
    private String name;
    private PersonDTO personDTO;

    public RoleDTO(String name) {
        this.name = name;
    }

    public RoleDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(name, roleDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
