package ru.job4j.chat.dto;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
public class PersonDTO {
    private String name;
    private String password;
    private List<RoleDTO> roleDTOS;

    public PersonDTO(String name, List<RoleDTO> roleDTO) {
        this.name = name;
        this.roleDTOS = roleDTO;
    }

    public PersonDTO() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDTO> getRoles() {
        return roleDTOS;
    }

    public void setRoles(List<RoleDTO> roleDTOS) {
        this.roleDTOS = roleDTOS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoleDTO> getRole() {
        return roleDTOS;
    }

    public void setRole(List<RoleDTO> roleDTO) {
        this.roleDTOS = roleDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(password, personDTO.password) && Objects.equals(roleDTOS, personDTO.roleDTOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, roleDTOS);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonDTO{");
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
