package ru.job4j.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
@Builder
@AllArgsConstructor
public class MessageDTO {
    private int id;
    @NotNull(message = "Message must be not null")
    private String text;
}
