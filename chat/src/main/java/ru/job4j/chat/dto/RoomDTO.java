package ru.job4j.chat.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
@Builder
public class RoomDTO {
    @NotNull(message = "Name must be not null")
    private String name;
    private String user;
    private String message;
}
