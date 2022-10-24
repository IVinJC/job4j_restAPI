package ru.job4j.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.job4j.chat.model.Person;

import javax.validation.constraints.NotNull;
@Data
@Builder
@AllArgsConstructor
public class RoleDTO {
    @NotNull(message = "Name must be not null")
    private String name;
    private Person person;
}
