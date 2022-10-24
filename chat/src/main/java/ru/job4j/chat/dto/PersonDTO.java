package ru.job4j.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.chat.model.Room;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {
    @NotNull(message = "Name must be not null")
    private String name;
    @NotNull(message = "Password must be not null")
    private String password;
    private Room room;
}
